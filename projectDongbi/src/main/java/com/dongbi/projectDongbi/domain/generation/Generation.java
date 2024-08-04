package com.dongbi.projectDongbi.domain.generation;

import com.dongbi.projectDongbi.domain.club.Club;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Generation {
    public BigDecimal setDeposit;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "generation_num", nullable = false)
    private Long generationNum;

    private boolean actFlag;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private BigDecimal amount;

    @Builder
    public Generation(Long generationNum ,String name, Club club, LocalDate startDate, LocalDate endDate){
        this.generationNum = generationNum;
        this.name = name;
        this.club = club;
        this.actFlag = true;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = BigDecimal.ZERO;
    }

    public void changeAmount(BigDecimal amount){
        this.amount = amount;
    }

}
