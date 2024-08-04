package com.dongbi.projectDongbi.domain.col;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Col {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id")
    private Long id;

    private String colName;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "generation_id")
    private Generation generation;

    public static Col createCol(String colName, BigDecimal price, Generation generation) {
        Col col = new Col();
        col.colName = colName;
        col.price = price;
        col.generation = generation;
        return col;
    }


}
