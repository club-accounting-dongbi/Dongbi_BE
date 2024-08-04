package com.dongbi.projectDongbi.domain.generation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGeneration is a Querydsl query type for Generation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGeneration extends EntityPathBase<Generation> {

    private static final long serialVersionUID = 1141302372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGeneration generation = new QGeneration("generation");

    public final BooleanPath actFlag = createBoolean("actFlag");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final com.dongbi.projectDongbi.domain.club.QClub club;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> generationNum = createNumber("generationNum", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> setDeposit = createNumber("setDeposit", java.math.BigDecimal.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QGeneration(String variable) {
        this(Generation.class, forVariable(variable), INITS);
    }

    public QGeneration(Path<? extends Generation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGeneration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGeneration(PathMetadata metadata, PathInits inits) {
        this(Generation.class, metadata, inits);
    }

    public QGeneration(Class<? extends Generation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new com.dongbi.projectDongbi.domain.club.QClub(forProperty("club")) : null;
    }

}

