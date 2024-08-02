package com.dongbi.projectDongbi.domain.paid.repository;

import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongbi.projectDongbi.domain.paid.QPaid.paid1;
import static com.dongbi.projectDongbi.domain.col.QCol.col;

public class PaidRepositoryImpl implements PaidCustomRepository{

    @Autowired
    JPAQueryFactory queryFactory;



    @Override
    public List<Paid> findByPaidInColNameAndClubMemberId(Long clubMemberId, Long generationNum, List<PaidRequest> paidList) {

        return queryFactory
                .selectFrom(paid1)
                .join(paid1.col, col).on(col.colName.in(
                       paidList.stream()
                               .map(PaidRequest::getColName)
                               .collect(Collectors.toList()))
                        .and(col.generation.generationNum.eq(generationNum)))
                .where(
                        paid1.clubMember.id.eq(clubMemberId),
                        col.price.in(
                                paidList.stream()
                                        .map(PaidRequest::getPrice)
                                        .collect(Collectors.toList())))
                .fetch();
    }
}
