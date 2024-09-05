package com.dongbi.projectDongbi.domain.transaction.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.domain.transaction.Banking;
import com.dongbi.projectDongbi.domain.transaction.Transaction;
import com.dongbi.projectDongbi.web.transaction.dto.request.TransactionConditionRequest;
import com.dongbi.projectDongbi.web.transaction.dto.response.QTransactionBankingResponse;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.dongbi.projectDongbi.domain.generation.QGeneration.generation;
import static com.dongbi.projectDongbi.domain.transaction.QTransaction.transaction;

public class TransactionRepositoryImpl implements TransactionCustomRepository{

    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public Page<TransactionBankingResponse> findTransaction(TransactionConditionRequest request, Pageable pageable) {

        List<TransactionBankingResponse> transactionList = queryFactory.select(
                        new QTransactionBankingResponse(
                                transaction.deposit,
                                transaction.withdrawal,
                                transaction.occurrenceDate,
                                transaction.occurrenceTime,
                                transaction.personCharge,
                                transaction.reason,
                                transaction.cash,
                                transaction.banking,
                                transaction.imagePath

                        ))
                .from(transaction)
                .join(transaction.generation, generation).on(generation.club.id.eq(request.getClubId()).and(generation.generationNum.eq(request.getGenerationNum())))
                .where(
                        dateBetween(request.getStartDate(), request.getEndDate()),
                        chkBanking(request.getBanking()),
                        searchReason(request.getReason())
                )
                .orderBy(transaction.occurrenceDate.desc(),transaction.occurrenceTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(transaction.count())
                .from(transaction)
                .join(transaction.generation, generation).on(generation.club.id.eq(request.getClubId()).and(generation.generationNum.eq(request.getGenerationNum())))
                .where(
                        dateBetween(request.getStartDate(), request.getEndDate()),
                        chkBanking(request.getBanking()),
                        searchReason(request.getReason())
                )
                .fetchCount();
       return new PageImpl<>(transactionList, pageable, count);

    }

    @Override
    public Transaction findDepositTransaction(ClubMember clubMember, Paid paid) {

        return queryFactory.select(transaction)
                .from(transaction)
                .join(transaction.generation, generation).on(generation.id.eq(clubMember.getGeneration().getId()))
                .where(transaction.personCharge.eq(paid.getClubMember().getName())
                        .and(transaction.deposit.eq(paid.getCol().getPrice())
                        .and(transaction.reason.eq(paid.getCol().getColName()))))
                .fetchOne();

    }

    @Override
    public List<TransactionBankingResponse> findAllTransaction(Long clubId, Long generationNum) {

        return queryFactory.select( new QTransactionBankingResponse(
                        transaction.deposit,
                        transaction.withdrawal,
                        transaction.occurrenceDate,
                        transaction.occurrenceTime,
                        transaction.personCharge,
                        transaction.reason,
                        transaction.cash,
                        transaction.banking,
                        transaction.imagePath

                ))
                .from(transaction)
                .where(generation.generationNum.eq(generationNum).and(generation.club.id.eq(clubId)))
                .fetch();
    }


    private BooleanExpression searchReason(String searchName) {
        return StringUtils.hasText(searchName) ? transaction.reason.in("%"+searchName+"%") : null;

    }

    private BooleanExpression chkBanking(Banking banking) {
        if(banking != null)
            return transaction.banking.eq(banking);
        else return null;
    }

    private BooleanExpression dateBetween(LocalDate startDate, LocalDate endDate) {
        if(startDate != null && endDate != null){
            return transaction.occurrenceDate.between(startDate, endDate);
        }else{
            return null;
        }
    }
}
