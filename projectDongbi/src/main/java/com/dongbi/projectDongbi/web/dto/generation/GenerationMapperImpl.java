package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongbi.projectDongbi.domain.club.QClub.club;
import static com.dongbi.projectDongbi.domain.clubmembers.QClubMember.clubMember;
import static com.dongbi.projectDongbi.domain.generation.QGeneration.generation;

@Component
@RequiredArgsConstructor
public class GenerationMapperImpl implements GenerationMapper{
    @Autowired
    private EntityManager em;

    private final JPAQueryFactory queryFactory;
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

    @Override
    public List<Long> findGenerationNum(Long clubId) {
        return em.createQuery("select g.generationNum from Generation g where g.club.id = :clubId",Long.class)
                .setParameter("clubId", clubId)
                .getResultList();

    }

    @Override
    public List<String> getNames(Long clubId, Long generationNum) {
        return  queryFactory.select(clubMember.name)
                .from(generation)
                .join(generation.clubMembers, clubMember).on(clubMember.generation.generationNum.eq(generationNum))
                .join(generation.club, club).on(club.id.eq(clubId))
                .fetch();
    }

    private List<ClubMemberResponse> toClubMemberResponseDtoList(List<ClubMember> members) {
        return members.stream()
                .map(member ->{
                    ClubMemberResponse memberDto = new ClubMemberResponse().builder()
                            .name(member.getName())
                            .del_flag(member.getDelFlag())
                            .act_flag(member.getActFlag())
                            .pays(toPaidResponseDtoList(member.getPay()))
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
