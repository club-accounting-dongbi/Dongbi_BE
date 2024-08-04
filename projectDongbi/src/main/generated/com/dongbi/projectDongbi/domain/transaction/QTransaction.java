package com.dongbi.projectDongbi.domain.transaction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = -676011324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final EnumPath<Banking> banking = createEnum("banking", Banking.class);

    public final NumberPath<java.math.BigDecimal> cash = createNumber("cash", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> deposit = createNumber("deposit", java.math.BigDecimal.class);

    public final com.dongbi.projectDongbi.domain.generation.QGeneration generation;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final DatePath<java.time.LocalDate> occurrenceDate = createDate("occurrenceDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> occurrenceTime = createTime("occurrenceTime", java.time.LocalTime.class);

    public final StringPath personCharge = createString("personCharge");

    public final StringPath reason = createString("reason");

    public final NumberPath<java.math.BigDecimal> withdrawal = createNumber("withdrawal", java.math.BigDecimal.class);

    public QTransaction(String variable) {
        this(Transaction.class, forVariable(variable), INITS);
    }

    public QTransaction(Path<? extends Transaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransaction(PathMetadata metadata, PathInits inits) {
        this(Transaction.class, metadata, inits);
    }

    public QTransaction(Class<? extends Transaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.generation = inits.isInitialized("generation") ? new com.dongbi.projectDongbi.domain.generation.QGeneration(forProperty("generation"), inits.get("generation")) : null;
    }

}

