package com.dongbi.projectDongbi.web.transaction.dto.request;

import com.dongbi.projectDongbi.domain.transaction.Banking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionConditionRequest {
    private Long clubId;
    private Long generationNum;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String reason;
    private Banking banking;

}
