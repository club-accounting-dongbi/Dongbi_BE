package com.dongbi.projectDongbi.domain.generation;

import com.dongbi.projectDongbi.domain.club.Club;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Generation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;


    @Column(name = "names", nullable = false)
    private String name;

    @Column(name = "generation_num", nullable = false)
    private Long generationNum;

    private boolean actFlag;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Builder
    public Generation(Long generationNum ,String name, Club club, LocalDateTime startDate, LocalDateTime endDate){
        this.generationNum = generationNum;
        this.name = name;
        this.club = club;
        this.actFlag = true;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
