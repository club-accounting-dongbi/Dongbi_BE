package com.dongbi.projectDongbi.domain.transaction;

import com.dongbi.projectDongbi.domain.BaseEntity;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.global.exception.TransactionException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction extends BaseEntity {

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

    public static Transaction depositTransaction(Generation generation, BigDecimal deposit, String reason, String personCharge, BigDecimal cash) {
        Transaction tr = new Transaction();
        tr.cash = cash.add(deposit);
        generation.changeAmount(tr.cash);
        tr.generation = generation;
        tr.deposit = deposit;
        tr.withdrawal = BigDecimal.ZERO;
        tr.reason = reason;
        tr.occurrenceDate = LocalDate.now();
        tr.occurrenceTime = LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute());
        tr.banking = Banking.DEPOSIT;
        tr.personCharge = personCharge;
        return tr;
    }

    public static Transaction withdrawalTransaction(Generation generation,BigDecimal withdrawal, LocalDate occurrenceDate, LocalTime occurrenceTime, String personCharge, String reason, BigDecimal cash, String imagePath) {
        Transaction tr = new Transaction();
        tr.withdrawal = withdrawal;
        tr.generation = generation;
        tr.deposit = BigDecimal.ZERO;
        tr.occurrenceDate = occurrenceDate;
        tr.occurrenceTime = occurrenceTime;
        tr.personCharge = personCharge;
        tr.reason = reason;
        if(cash.subtract(withdrawal).compareTo(BigDecimal.ZERO) < 0) throw new TransactionException("보유금액보다 지출금액이 클 수 없습니다.");
        tr.cash = cash.subtract(withdrawal);
        generation.changeAmount(tr.cash);
        tr.imagePath = imagePath;
        tr.banking = Banking.WITHDRAW;
        return tr;
    }

}
