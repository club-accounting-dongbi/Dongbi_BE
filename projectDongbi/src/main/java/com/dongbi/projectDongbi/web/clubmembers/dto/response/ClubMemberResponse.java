package com.dongbi.projectDongbi.web.clubmembers.dto.response;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.dto.generation.GenerationAndClubMemberResDto;
import com.dongbi.projectDongbi.web.paid.dto.PaidResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClubMemberResponse {
    private GenerationAndClubMemberResDto generation;
    private String name;
    private List<PaidResponseDto> pay;
    private Boolean del_flag;
    private Boolean act_flag;


    @Builder
    public ClubMemberResponse(Generation generation, String name, List<Paid> pays, Boolean del_flag, Boolean act_flag) {
        this.generation = GenerationAndClubMemberResDto.fromEntity(generation);
        this.name = name;
        this.pay = pays.stream()
                .map(PaidResponseDto::fromEntity)
                .collect(Collectors.toList());
        this.del_flag = del_flag;
        this.act_flag = act_flag;
    }






}
