package com.dongbi.projectDongbi.domain.generation.repository;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.QGeneration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.dongbi.projectDongbi.domain.club.QClub.club;
import static com.dongbi.projectDongbi.domain.generation.QGeneration.generation;

@Repository
public class GenerationCustomRepositoryImpl implements GenerationCustomRepository{

    @Autowired
    private JPAQueryFactory queryFactory;


    @Override
    public Generation findByClubMemberIdAndGenerationNum(Long userId, Long generationNum) {

        QGeneration subGeneration = new QGeneration("subGeneration");
        return queryFactory.select(generation)
                .from(generation)
//                .join(generation.club, club).on(club.user.id.eq(userId))
                .where(
                        generation.generationNum.eq(generationNum)
                )
                .fetchOne();

    }
}
