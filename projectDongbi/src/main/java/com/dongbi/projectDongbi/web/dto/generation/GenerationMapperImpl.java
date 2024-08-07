package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenerationMapperImpl implements GenerationMapper{

    @Override
    public GenerationResponseDto toResponseDto(Generation generation) {
        GenerationResponseDto responseDto = new GenerationResponseDto().builder()
                .id(generation.getId())
                .name(generation.getName())
                .generationNum(generation.getGenerationNum())
                .startDate(generation.getStartDate())
                .endDate(generation.getEndDate())
                .amount(generation.getAmount())
                .actFlag(generation.isActFlag())
                .members(toClubMemberResponseDtoList(generation.getClubMembers()))
                .build();
        return responseDto;
    }

    private List<ClubMemberResponse> toClubMemberResponseDtoList(List<ClubMember> members) {
        return members.stream()
                .map(member ->{
                    ClubMemberResponse memberDto = new ClubMemberResponse().builder()
                            .name(member.getName())
                            .del_flag(member.getDelFlag())
                            .act_flag(member.getActFlag())
                            .pay(toPaidResponseDtoList(member.getPay()))
                            .build();
                    return memberDto;
                })
                .collect(Collectors.toList());
    }

    private List<Paid> toPaidResponseDtoList(List<Paid> pays){
        return pays.stream()
                .collect(Collectors.toList());
    }
}
