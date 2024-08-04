package com.dongbi.projectDongbi.domain.paid;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaid is a Querydsl query type for Paid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaid extends EntityPathBase<Paid> {

    private static final long serialVersionUID = -2116794780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaid paid1 = new QPaid("paid1");

    public final com.dongbi.projectDongbi.domain.clubmembers.QClubMember clubMember;

    public final com.dongbi.projectDongbi.domain.col.QCol col;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath paid = createBoolean("paid");

    public QPaid(String variable) {
        this(Paid.class, forVariable(variable), INITS);
    }

    public QPaid(Path<? extends Paid> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaid(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaid(PathMetadata metadata, PathInits inits) {
        this(Paid.class, metadata, inits);
    }

    public QPaid(Class<? extends Paid> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubMember = inits.isInitialized("clubMember") ? new com.dongbi.projectDongbi.domain.clubmembers.QClubMember(forProperty("clubMember"), inits.get("clubMember")) : null;
        this.col = inits.isInitialized("col") ? new com.dongbi.projectDongbi.domain.col.QCol(forProperty("col"), inits.get("col")) : null;
    }

}

