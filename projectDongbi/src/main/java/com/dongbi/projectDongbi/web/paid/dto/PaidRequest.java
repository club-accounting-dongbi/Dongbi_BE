package com.dongbi.projectDongbi.web.paid.dto;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaidRequest {
    String colName;
    BigDecimal price;
    Boolean paid;

    public PaidRequest(String colName, BigDecimal price, Boolean paid) {
        this.colName = colName;
        this.price = price;
        this.paid = paid;
    }
}
