package com.dongbi.projectDongbi.web.paid.dto;

import com.dongbi.projectDongbi.domain.paid.Paid;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaidResponseDto {
    private Long id;
    private Boolean paid;
    private String colName;
    private BigDecimal price;

    public PaidResponseDto(Long id, Boolean paid, String colName, BigDecimal price) {
        this.id = id;
        this.paid = paid;
        this.colName = colName;
        this.price = price;
    }

    // Paid 엔티티를 PaidResponseDto로 변환하는 메서드
    public static PaidResponseDto fromEntity(Paid paid) {
        return PaidResponseDto.builder()
                .id(paid.getId())
                .paid(paid.getPaid())
                .colName(paid.getCol().getColName())
                .price(paid.getCol().getPrice())
                .build();
    }
}