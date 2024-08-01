package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.SearchClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongbi.projectDongbi.domain.club.QClub.club;
import static com.dongbi.projectDongbi.domain.clubmembers.QClubMember.clubMember;
import static com.dongbi.projectDongbi.domain.generation.QGeneration.generation;
import static com.dongbi.projectDongbi.domain.paid.QPaid.paid1;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClubMemberCustomRepositoryImpl implements ClubMemberCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClubMemberResponse> findByClubIdAndGenerationNum(SearchClubMemberRequest request) {

        List<ClubMember> members = queryFactory
                .selectFrom(clubMember)
                .join(clubMember.generation, generation).fetchJoin()
                .where(
                        club.id.eq(request.getClubId()),
                        generation.generationNum.eq(request.getGenerationNum()),
                                clubMember.delFlag.isFalse()

                )
                .orderBy(clubMember.actFlag.asc(),clubMember.name.asc())
                .fetch();

        List<Paid> paidList = queryFactory
                .selectFrom(paid1)
                .join(paid1.clubMember, clubMember).fetchJoin()
                .where(
                       paid1.clubMember.in(
                               members
                        )
                )
                .fetch();
        return members.stream()
                .map(member -> {
                    List<Paid> memberPays = paidList.stream()
                            .filter(p -> p.getClubMember().getId().equals(member.getId()))
                            .collect(Collectors.toList());

                    return new ClubMemberResponse(
                            member.getGeneration(),
                            member.getName(),
                            memberPays,
                            member.getDelFlag(),
                            member.getActFlag()
                    );
                })
                .collect(Collectors.toList());


    }



    @Override
    public ClubMember findByClubIdAndName(Long clubId, String name) {
        return queryFactory.select(clubMember)
                .from(clubMember)
                .join(clubMember.generation, generation).on(generation.club.id.eq(clubId))
                .where(clubMember.name.eq(name))
                .fetchOne();

    }

    @Override
    public Long existsByMember(CreateClubMemberRequest request) {

        return queryFactory.select(clubMember.count())
                .from(clubMember)
                .join(clubMember.generation, generation).on(generation.club.id.eq(request.clubId()))
                .where(clubMember.name.in(request.names()))
                .fetchOne();
    }


}
