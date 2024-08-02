package com.dongbi.projectDongbi.web.transaction.dto.response;

import com.dongbi.projectDongbi.domain.transaction.Banking;
import com.dongbi.projectDongbi.domain.transaction.Transaction;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@ToString
public class TransactionBankingResponse {
    private BigDecimal deposit;
    private BigDecimal withdrawal;
    private LocalDate occurrenceDate;
    private LocalTime occurrenceTime;
    private String personCharge;
    private String reason;
    private BigDecimal cash;
    private Banking banking;
    private String filepath;

    @QueryProjection
    public TransactionBankingResponse(BigDecimal deposit, BigDecimal withdrawal, LocalDate occurrence_date, LocalTime occurrence_time, String person_charge, String reason, BigDecimal cash, Banking banking, String filepath) {
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.occurrenceDate = occurrence_date;
        this.occurrenceTime = occurrence_time;
        this.personCharge = person_charge;
        this.reason = reason;
        this.cash = cash;
        this.banking = banking;
        this.filepath = filepath;
    }




    public TransactionBankingResponse(Transaction tr){
        this.deposit = tr.getDeposit();
        this.withdrawal = tr.getWithdrawal();
        this.occurrenceDate = tr.getOccurrenceDate();
        this.occurrenceTime = tr.getOccurrenceTime();
        this.personCharge = tr.getPersonCharge();
        this.reason = tr.getReason();
        this.cash = tr.getCash();
        this.banking = tr.getBanking();
    }

    private BigDecimal calculateCash(BigDecimal deposit, BigDecimal withdrawal) {
        return deposit.subtract(withdrawal);
    }



}
