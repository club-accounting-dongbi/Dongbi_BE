package com.dongbi.projectDongbi.domain.common.file.service;

import com.dongbi.projectDongbi.domain.common.file.File;
import com.dongbi.projectDongbi.domain.common.file.repository.FileRepository;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;


    @Value("${file.upload-dir}")
    private String uploadDir;

    public File saveFile(MultipartFile file) throws IOException{

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be null or empty");
        }

        Path directoryPath = Paths.get(uploadDir);
        if(!Files.exists(directoryPath)){
            Files.createDirectories(directoryPath);
        }
        String fileName = getFileName(file, directoryPath.toString());

        Path filePath = directoryPath.resolve(fileName);
        String standardizedFilePath = filePath.toString().replace("\\", "/");
        Files.write(filePath, file.getBytes());

        File uploadFile = new File(fileName, standardizedFilePath);

        return fileRepository.save(uploadFile);
    }

    public Resource getFile(String filePath) throws  Exception{

        File file = fileRepository.findByFilePath(filePath).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"파일경로를 찾을 수 없습니다."));
        Path path = Paths.get(file.getFilePath());
        if(!Files.exists(path)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "파일이 저장되어있지 않습니다.");
        }

        return new UrlResource(path.toUri());
    }



    private String getFileName(MultipartFile image, String imagePath) {
        String originalFileName = image.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return  UUID.randomUUID() + extension;
    }

    public byte[] generateExcel(Page<TransactionBankingResponse> responseData) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("입출금 내역");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("날짜");
        headerRow.createCell(1).setCellValue("시간");
        headerRow.createCell(2).setCellValue("담당자");
        headerRow.createCell(3).setCellValue("이유");
        headerRow.createCell(4).setCellValue("입금액");
        headerRow.createCell(5).setCellValue("출금액");
        headerRow.createCell(6).setCellValue("잔액");

        int rowNum = 1;
        for(TransactionBankingResponse tr : responseData.getContent()){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(tr.getOccurrenceDate()));
            row.createCell(1).setCellValue(String.valueOf(tr.getOccurrenceTime()));
            row.createCell(2).setCellValue(tr.getPersonCharge());
            row.createCell(3).setCellValue(tr.getReason());
            row.createCell(4).setCellValue(String.valueOf(tr.getDeposit()));
            row.createCell(5).setCellValue(String.valueOf(tr.getWithdrawal()));
            row.createCell(6).setCellValue(String.valueOf(tr.getCash()));
        }


        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }finally{
            workbook.close();
        }


          }



    public byte[] generatePdf(Page<TransactionBankingResponse> responseData) throws IOException {


        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            String fontFilePath = "C:\\Windows\\Fonts\\malgun.ttf";
            BaseFont baseFont = BaseFont.createFont(fontFilePath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontTitle = new Font(baseFont,12);
            Font fondRows = new Font(baseFont,10);

            Font font = new Font(baseFont, 12);
            Paragraph titleParagraph = new Paragraph("입출금 내역", font);
            titleParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(titleParagraph);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.addCell(new PdfPCell(new Paragraph("날짜", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("시간", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("담당자", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("이유", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("입금액", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("출금액", fontTitle)));
            table.addCell(new PdfPCell(new Paragraph("잔액", fontTitle)));


            for (TransactionBankingResponse tr : responseData.getContent()) {
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(tr.getOccurrenceDate()), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(tr.getOccurrenceTime()), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(tr.getPersonCharge(), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(tr.getReason(), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(tr.getDeposit().toPlainString(), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(tr.getWithdrawal().toPlainString(), fondRows)));
                table.addCell(new PdfPCell(new Paragraph(tr.getCash().toPlainString(), fondRows)));
            }



            document.add(table);
            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
