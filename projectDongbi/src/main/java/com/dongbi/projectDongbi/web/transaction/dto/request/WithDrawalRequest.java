package com.dongbi.projectDongbi.web.transaction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WithDrawalRequest {

    @Schema(description = "동아리 Id", nullable = false, example = "1")
    private Long clubId;
    @Schema(description = "동아리 기수 번호", nullable = false, example = "1")
    private Long generationNum;
    @Schema(description = "지출 일자", nullable = false, example = "2024-07-31")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate occurenceDate;
    @Schema(description = "지출 시각", nullable = false, example = "23:15")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime occurenceTime;
    @Schema(description = "담당자", nullable = false, example = "김삼현")
    private String personCharge;
    @Schema(description = "금액", nullable = false, example = "10000")
    private BigDecimal price;
    @Schema(description = "이유", nullable = false, example = "MT")
    private String reason;

}
