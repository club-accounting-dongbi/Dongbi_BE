package com.dongbi.projectDongbi.domain.transaction;

import com.dongbi.projectDongbi.domain.generation.Generation;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "Deposit", columnDefinition = "decimal default 0")
    private BigDecimal deposit;

    @Column(name = "withdrawal", columnDefinition = "decimal default 0")
    private BigDecimal withdrawal;

    @Column(name = "occurence_date", nullable = false)
    private LocalDate occurrence_date;
    @Column(name = "occurrence_time", nullable = false)
    private LocalTime occurrence_time;

    @Column(name = "person_charge", length = 10)
    private String person_charge;

    @Column(name = "reason", length = 50)
    private String reason;
    @Column(name = "cash", columnDefinition = "decimal default 0")
    private BigDecimal cash;

    @Column(name = "banking", nullable = false)
    private boolean banking;

    @Column(name = "imagePath", length = 30)
    private String imagePath;

    public Transaction(Generation generation, BigDecimal deposit, BigDecimal withdrawal,
                       LocalDate occurrence_date, LocalTime occurrence_time,
                       String person_charge, String reason, BigDecimal cash) {
        this.generation = generation;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.occurrence_date = occurrence_date;
        this.occurrence_time = occurrence_time;
        this.person_charge = person_charge;
        this.reason = reason;
        this.cash = cash.add(deposit).subtract(withdrawal);
        this.banking = deposit.compareTo(BigDecimal.ZERO) > 0;
    }
    private BigDecimal calculateCash(BigDecimal deposit, BigDecimal withdrawal) {
        return deposit.subtract(withdrawal);
    }

}
