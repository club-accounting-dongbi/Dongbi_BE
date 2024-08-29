package com.dongbi.projectDongbi.domain.col;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCol is a Querydsl query type for Col
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCol extends EntityPathBase<Col> {

    private static final long serialVersionUID = -684139768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCol col = new QCol("col");

    public final StringPath colName = createString("colName");

    public final com.dongbi.projectDongbi.domain.generation.QGeneration generation;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QCol(String variable) {
        this(Col.class, forVariable(variable), INITS);
    }

    public QCol(Path<? extends Col> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCol(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCol(PathMetadata metadata, PathInits inits) {
        this(Col.class, metadata, inits);
    }

    public QCol(Class<? extends Col> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.generation = inits.isInitialized("generation") ? new com.dongbi.projectDongbi.domain.generation.QGeneration(forProperty("generation"), inits.get("generation")) : null;
    }

}

