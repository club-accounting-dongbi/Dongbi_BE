package com.dongbi.projectDongbi.domain.clubmembers;

import com.dongbi.projectDongbi.domain.BaseEntity;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClubMember extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Generation generation;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "paid", cascade =  CascadeType.ALL, orphanRemoval = true)
    @Column(updatable = false)
    private List<Paid> pay = new ArrayList<>();

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "act_flag")
    private Boolean actFlag;



    public static ClubMember createClubMember(String name, Generation generation) {
        ClubMember cm = new ClubMember();

        // 첫 글자가 숫자인지 확인
        if (Character.isDigit(name.charAt(0))) {
            cm.name = name; // 첫 글자가 숫자면 name만 설정
        } else {
            cm.name = generation.getGenerationNum() + "기 " + name; // 기존 로직
        }

        cm.generation = generation;
        cm.delFlag = false;
        cm.actFlag = false;

        return cm;
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
