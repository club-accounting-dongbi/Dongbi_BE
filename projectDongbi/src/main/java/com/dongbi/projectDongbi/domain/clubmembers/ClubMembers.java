package com.dongbi.projectDongbi.domain.clubmembers;

import com.dongbi.projectDongbi.domain.generation.Generation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ClubMembers {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Generation generation;


    @Column(length = 5)
    private String name;

    private List<Boolean> withdraw = new ArrayList<Boolean>();

    private boolean del_flag;


    @Builder
    public ClubMembers(String name, Generation generation){

        this.name = name;
        this.generation = generation;
        this.del_flag = false;
    }


    public ClubMembers deleteClubMembers(boolean del_flag){
        this.del_flag = !del_flag;
        return this;
    }

    public ClubMembers updateClubMembers(String name, List<Boolean> withdraw){
        this.name = name;
        this.withdraw = withdraw;
        return this;
    }




}
