package com.dongbi.projectDongbi.web.clubmembers.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dongbi.projectDongbi.web.clubmembers.dto.response.QClubMemberResponse is a Querydsl Projection type for ClubMemberResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QClubMemberResponse extends ConstructorExpression<ClubMemberResponse> {

    private static final long serialVersionUID = 2085721738L;

    public QClubMemberResponse(com.querydsl.core.types.Expression<? extends com.dongbi.projectDongbi.domain.generation.Generation> generation, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<? extends java.util.List<com.dongbi.projectDongbi.domain.paid.Paid>> pay, com.querydsl.core.types.Expression<Boolean> del_flag, com.querydsl.core.types.Expression<Boolean> act_flag) {
        super(ClubMemberResponse.class, new Class<?>[]{com.dongbi.projectDongbi.domain.generation.Generation.class, String.class, java.util.List.class, boolean.class, boolean.class}, generation, name, pay, del_flag, act_flag);
    }

}

