package com.dongbi.projectDongbi.domain.generation;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Generation {
    public BigDecimal setDeposit;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "generation", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ClubMember> clubMembers = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "generation_num")
    private Long generationNum;

    private boolean actFlag;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private BigDecimal amount;

    @Builder
    public Generation(String name, Club club, LocalDate startDate, LocalDate endDate){
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

    public List<String> getMemberNames(){
        return clubMembers.stream()
                .map(ClubMember::getName)
                .collect(Collectors.toList());
    }

    public void updateEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

}
