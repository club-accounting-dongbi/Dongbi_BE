package com.dongbi.projectDongbi.web.clubmembers.dto.response;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@Data
@Builder
public class ClubMemberResponse {
    private Generation generation;
    private String name;
    private List<Paid> pay;
    private Boolean del_flag;
    private Boolean act_flag;


//    public ClubMemberResponse(ClubMember member) {
//        this.generation = member.getGeneration();
//        this.names = member.getName();
//        this.del_flag = member.isDelFlag();
//        this.pay = member.getPay();
//    }

    @QueryProjection
    public ClubMemberResponse(Generation generation, String name, List<Paid> pay, Boolean del_flag,Boolean act_flag) {
        this.generation = generation;
        this.name = name;
        this.pay = pay;
        this.del_flag = del_flag;
        this.act_flag = act_flag;
    }


}
