package com.dongbi.projectDongbi.web.transaction.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dongbi.projectDongbi.web.transaction.dto.response.QTransactionBankingResponse is a Querydsl Projection type for TransactionBankingResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTransactionBankingResponse extends ConstructorExpression<TransactionBankingResponse> {

    private static final long serialVersionUID = 221368893L;

    public QTransactionBankingResponse(com.querydsl.core.types.Expression<? extends java.math.BigDecimal> deposit, com.querydsl.core.types.Expression<? extends java.math.BigDecimal> withdrawal, com.querydsl.core.types.Expression<java.time.LocalDate> occurrence_date, com.querydsl.core.types.Expression<java.time.LocalTime> occurrence_time, com.querydsl.core.types.Expression<String> person_charge, com.querydsl.core.types.Expression<String> reason, com.querydsl.core.types.Expression<? extends java.math.BigDecimal> cash, com.querydsl.core.types.Expression<com.dongbi.projectDongbi.domain.transaction.Banking> banking, com.querydsl.core.types.Expression<String> filepath) {
        super(TransactionBankingResponse.class, new Class<?>[]{java.math.BigDecimal.class, java.math.BigDecimal.class, java.time.LocalDate.class, java.time.LocalTime.class, String.class, String.class, java.math.BigDecimal.class, com.dongbi.projectDongbi.domain.transaction.Banking.class, String.class}, deposit, withdrawal, occurrence_date, occurrence_time, person_charge, reason, cash, banking, filepath);
    }

}

