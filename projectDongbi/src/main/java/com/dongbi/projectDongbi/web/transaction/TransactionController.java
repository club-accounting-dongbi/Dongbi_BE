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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
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
import java.util.List;

@RestController
@Tag(name = "입출금 컨트롤러", description = "입출금 Api 입니다.")
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final FileService fileService;
    private final S3Service s3Service;

    @Operation(summary = "입출금 내역 조회", description = "입출금 내역을 검색조건에 따라 조회합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<TransactionBankingResponse>>> getTransactionList(
            @ParameterObject TransactionConditionRequest request,
            @Parameter(description = "페이지 정보", schema = @Schema(implementation = Pageable.class))
            @PageableDefault(size = 10, sort = "occurence_date",direction = Sort.Direction.ASC) Pageable pageable

    ){
        Page<TransactionBankingResponse> result = transactionService.getTransactionList(request, pageable);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "입금", description = "입금")
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<Void>> createDeposit(@RequestBody DepositRequest request){
        transactionService.createDeposit(request);

        return ResponseEntity.ok(ApiResponse.success());
    }

    @Operation(summary = "출금", description = "출금")
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<Void>> createWithdraw(
            @Schema(description = "파일", nullable = false, example = "파일")
            @RequestPart("file") MultipartFile file
            , @RequestPart WithDrawalRequest request) throws IOException {
        UploadImageResponse savedFile = s3Service.uploadImage(file);
        transactionService.createWithdraw(request, savedFile.getImagePath());
        return ResponseEntity.ok(ApiResponse.success());
    }

    @Operation(summary = "입출금 내역 다운로드", description = "입출금 내역을 pdf나 excel로 다운로드합니다.")
    @GetMapping("download/{type}")
    public ResponseEntity<byte[]> downloadTransaction(
            @Parameter(description = "다운로드 type",  example = "pdf / excel")
            @PathVariable String type,
            @Parameter(description = "동아리 Id", example = "1")
            Long clubId,
            @Parameter(description = "기수번호", example = "1")
            Long generationNum) throws IOException{
    List<TransactionBankingResponse> result = transactionService.getAllTransactionList(clubId, generationNum);

        if(result.isEmpty()){
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
