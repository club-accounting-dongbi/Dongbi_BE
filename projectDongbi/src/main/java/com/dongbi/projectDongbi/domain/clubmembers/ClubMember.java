package com.dongbi.projectDongbi.domain.clubmembers;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.domain.paid.repository.PaidRepository;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
public class ClubMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Generation generation;


    @Column(name = "names", nullable = false, length = 5)
    private String name;

    @OneToMany(mappedBy = "paid", cascade =  CascadeType.ALL, orphanRemoval = true)
    @Column(updatable = false)
    private List<Paid> pay = new ArrayList<>();

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "act_flag")
    private Boolean actFlag;


    @Builder
    public ClubMember(String name, Generation generation){

        this.name = name;
        this.generation = generation;
        this.delFlag = false;
        this.actFlag = false;
    }


    public void updateClubMember(UpdateClubMemberRequest request) {
        if(request.getDelFlag() != null){
            this.delFlag = request.getDelFlag();
        }
        if(request.getActFlag() != null){
            this.actFlag = request.getActFlag();
        }

    }
}
