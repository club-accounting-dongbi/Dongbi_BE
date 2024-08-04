package com.dongbi.projectDongbi.web.col.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ColGenerateRequest {
    private Long clubId;
    private Long generationNum;
    private String colName;
    private BigDecimal price;

    public ColGenerateRequest(Long clubId, Long generationNum, String colName, BigDecimal price) {
        this.clubId = clubId;
        this.generationNum = generationNum;
        this.colName = colName;
        this.price = price;
    }
}
