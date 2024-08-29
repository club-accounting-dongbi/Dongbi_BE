package com.dongbi.projectDongbi.web.transaction;

import com.dongbi.projectDongbi.domain.common.file.service.FileService;
import com.dongbi.projectDongbi.domain.s3.dto.UploadImageResponse;
import com.dongbi.projectDongbi.domain.s3.service.S3Service;
import com.dongbi.projectDongbi.domain.transaction.service.TransactionService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.global.exception.TransactionException;
import com.dongbi.projectDongbi.web.transaction.dto.request.DepositRequest;
import com.dongbi.projectDongbi.web.transaction.dto.request.TransactionConditionRequest;
import com.dongbi.projectDongbi.web.transaction.dto.request.WithDrawalRequest;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final FileService fileService;
    private final S3Service s3Service;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<TransactionBankingResponse>>> getTransactionList(
            @RequestBody TransactionConditionRequest request,
            @PageableDefault(size = 10, sort = "occurence_date",direction = Sort.Direction.ASC) Pageable pageable

    ){
        Page<TransactionBankingResponse> result = transactionService.getTransactionList(request, pageable);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<Void>> createDeposit(@RequestBody DepositRequest request){
        transactionService.createDeposit(request);

        return ResponseEntity.ok(ApiResponse.success());
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<Void>> createWithdraw(@RequestPart("file") MultipartFile file , @RequestPart WithDrawalRequest request) throws IOException {
        UploadImageResponse savedFile = s3Service.uploadImage(file);
        transactionService.createWithdraw(request, savedFile.getImagePath());
        return ResponseEntity.ok(ApiResponse.success());
    }


    @GetMapping("download/{type}")
    public ResponseEntity<byte[]> downloadTransaction(@PathVariable String type, @RequestBody TransactionConditionRequest request,
                                                      @PageableDefault(size = 10, sort = "occurence_date",direction = Sort.Direction.ASC) Pageable pageable) throws IOException{
    Page<TransactionBankingResponse> result = transactionService.getTransactionList(request, pageable);

        if(result.getContent().isEmpty()){
            throw new TransactionException("입출금내역이 없습니다.");
        }
        if(type.equals("excel")){
            byte[] excelContent = fileService.generateExcel(result);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"transactions.xlsx\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelContent);
        }else if(type.equals("pdf")){
            byte[] pdfContent = fileService.generatePdf(result);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"transactions.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfContent);
        }else {

            throw new IOException("파일생성에 문제가 발생하였습니다.");
        }
    }


}
