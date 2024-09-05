package com.dongbi.projectDongbi.web.transaction.dto.request;

import com.dongbi.projectDongbi.domain.transaction.Banking;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionConditionRequest {
    @Schema(description = "동아리 Id", nullable = false, example = "1")
    private Long clubId;
    @Schema(description = "동아리 기수 번호", nullable = false, example = "1")
    private Long generationNum;
    @Schema(description = "시작일자", nullable = true, example = "2024-09-02")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @Schema(description = "종료일자", nullable = true, example = "2024-09-02")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    @Schema(description = "이유", nullable = true, example = "할맥")
    private String reason;
    @Schema(description = "입금 or 출금 ", nullable = true, example = "DEPOSIT , WITHDRAW")
    private Banking banking;

}
