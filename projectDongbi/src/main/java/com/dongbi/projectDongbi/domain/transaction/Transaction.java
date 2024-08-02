package com.dongbi.projectDongbi.domain.transaction;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.global.exception.TransactionException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Generation generation;

    @Column(name = "Deposit",  columnDefinition = "decimal default 0")
    private BigDecimal deposit;

    @Column(name = "withdrawal", columnDefinition = "decimal default 0")
    private BigDecimal withdrawal;

    @Column(name = "occurence_date", nullable = false)
    private LocalDate occurrenceDate;
    @Column(name = "occurrence_time", nullable = false)
    private LocalTime occurrenceTime;

    @Column(name = "person_charge", length = 10)
    private String personCharge;

    @Column(name = "reason", length = 50)
    private String reason;
    @Column(name = "cash", columnDefinition = "decimal default 0")
    private BigDecimal cash;

    @Column(name = "banking", nullable = false)
    @Enumerated(EnumType.STRING)
    private Banking banking;

    private String imagePath;

    public Transaction(Generation generation, BigDecimal deposit, String reason, String personCharge, BigDecimal cash) {
        this.cash = cash.add(deposit);
        generation.changeAmount(this.cash);
        this.generation = generation;
        this.deposit = deposit;
        this.withdrawal = BigDecimal.ZERO;
        this.reason = reason;
        this.occurrenceDate = LocalDate.now();
        this.occurrenceTime = LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute());
        this.banking = Banking.DEPOSIT;
        this.personCharge = personCharge;
    }

    public Transaction(Generation generation,BigDecimal withdrawal, LocalDate occurrenceDate, LocalTime occurrenceTime, String personCharge, String reason, BigDecimal cash, String imagePath) {
        this.withdrawal = withdrawal;
        this.generation = generation;
        this.deposit = BigDecimal.ZERO;
        this.occurrenceDate = occurrenceDate;
        this.occurrenceTime = occurrenceTime;
        this.personCharge = personCharge;
        this.reason = reason;
        if(cash.subtract(withdrawal).compareTo(BigDecimal.ZERO) < 0) throw new TransactionException("보유금액보다 지출금액이 클 수 없습니다.");
        this.cash = cash.subtract(withdrawal);
        generation.changeAmount(this.cash);
        this.imagePath = imagePath;
        this.banking = Banking.WITHDRAW;
    }

    private BigDecimal calculateCash(BigDecimal deposit, BigDecimal withdrawal) {
        return deposit.subtract(withdrawal);
    }

}
