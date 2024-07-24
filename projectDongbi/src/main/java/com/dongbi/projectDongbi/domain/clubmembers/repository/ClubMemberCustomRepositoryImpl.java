package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongbi.projectDongbi.domain.club.QClub.club;
import static com.dongbi.projectDongbi.domain.clubmembers.QClubMember.clubMember;
import static com.dongbi.projectDongbi.domain.generation.QGeneration.generation;
import static com.dongbi.projectDongbi.domain.paid.QPaid.paid1;

@Repository
@RequiredArgsConstructor
public class ClubMemberCustomRepositoryImpl implements ClubMemberCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClubMemberResponse> findByClubIdAndGenerationNum(Long clubId, Long generationNum) {

        List<ClubMember> members = queryFactory
                .selectFrom(clubMember)
                .join(clubMember.generation, generation)
                .where(
                        club.id.eq(clubId),
                        generation.generationNum.eq(generationNum),
                                clubMember.delFlag.isFalse()

                )
                .orderBy(clubMember.actFlag.asc(),clubMember.name.asc())
                .fetch();

        List<Paid> paidList = queryFactory
                .selectFrom(paid1)
                .join(paid1.clubMember, clubMember)
                .where(
                        clubMember.generation.id.in(
                                members.stream().map(member -> member.getGeneration().getId()).collect(Collectors.toList())
                        )
                )
                .fetch();

        return members.stream()
                .map(member -> {
                    List<Paid> memberPays = paidList.stream()
                            .filter(p -> p.getClubMember().equals(member))
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
