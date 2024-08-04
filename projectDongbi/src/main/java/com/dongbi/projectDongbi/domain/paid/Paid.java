package com.dongbi.projectDongbi.domain.paid;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.col.Col;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paid {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paid_id")
    private Long id;

    @Column(name = "paid", columnDefinition = "boolean default false")
    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "club_member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ClubMember clubMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "col_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private Col col;




    @Builder
    public Paid(Boolean paid, ClubMember clubMember,Col col){
        this.paid = paid;
        this.clubMember = clubMember;
        this.col = col;
    }


    public Paid(Boolean paid, String name) {
        this.paid = paid;

    }

    public void updatePaid(ClubMember clubMember,Boolean paid) {
        if(paid != null) {
            clubMember.getPay().remove(this);
            this.paid = paid;
            clubMember.getPay().add(this);
        }
    }
}
