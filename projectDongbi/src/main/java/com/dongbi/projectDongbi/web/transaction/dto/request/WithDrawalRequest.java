package com.dongbi.projectDongbi.web.transaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WithDrawalRequest {

    private Long clubId;
    private Long generationNum;
    private LocalDate occurenceDate;
    private LocalTime occurenceTime;
    private String personCharge;
    private BigDecimal price;
    private String reason;

}
