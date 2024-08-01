package com.dongbi.projectDongbi.web.transaction.dto.request;

import com.dongbi.projectDongbi.domain.transaction.Banking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionConditionRequest {
    Long clubId;
    Long generationNum;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    Banking banking;

}
