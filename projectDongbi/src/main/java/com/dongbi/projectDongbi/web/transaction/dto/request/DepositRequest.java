package com.dongbi.projectDongbi.web.transaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {

    private Long clubId;
    private Long generationNum;
    private String colName;
    private BigDecimal price;
    private List<String> names;


}
