package com.dongbi.projectDongbi.domain.clubmembers;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMember is a Querydsl query type for ClubMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubMember extends EntityPathBase<ClubMember> {

    private static final long serialVersionUID = 361570405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubMember clubMember = new QClubMember("clubMember");

    public final BooleanPath actFlag = createBoolean("actFlag");

    public final BooleanPath delFlag = createBoolean("delFlag");

    public final com.dongbi.projectDongbi.domain.generation.QGeneration generation;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.dongbi.projectDongbi.domain.paid.Paid, com.dongbi.projectDongbi.domain.paid.QPaid> pay = this.<com.dongbi.projectDongbi.domain.paid.Paid, com.dongbi.projectDongbi.domain.paid.QPaid>createList("pay", com.dongbi.projectDongbi.domain.paid.Paid.class, com.dongbi.projectDongbi.domain.paid.QPaid.class, PathInits.DIRECT2);

    public QClubMember(String variable) {
        this(ClubMember.class, forVariable(variable), INITS);
    }

    public QClubMember(Path<? extends ClubMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubMember(PathMetadata metadata, PathInits inits) {
        this(ClubMember.class, metadata, inits);
    }

    public QClubMember(Class<? extends ClubMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.generation = inits.isInitialized("generation") ? new com.dongbi.projectDongbi.domain.generation.QGeneration(forProperty("generation"), inits.get("generation")) : null;
    }

}

