package com.dongbi.projectDongbi.domain.row;

import com.dongbi.projectDongbi.domain.generation.Generation;
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
    @JoinColumn(name = "generation_id")
    Generation generation;

    public Col(String colName, BigDecimal price, Generation generation) {
        this.colName = colName;
        this.price = price;
        this.generation = generation;
    }


}
