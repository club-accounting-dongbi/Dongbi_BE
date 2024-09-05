package com.dongbi.projectDongbi.web.col.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ColGenerateRequest {

    @Schema(description = "동아리 Id", nullable = false, example = "1")
    private Long clubId;
    @Schema(description = "동아리 기수 번호", nullable = false, example = "1")
    private Long generationNum;
    @Schema(description = "컬럼 명", nullable = false, example = "MT")
    private String colName;
    @Schema(description = "금액", nullable = false, example = "10000")
    private BigDecimal price;

    public ColGenerateRequest(Long clubId, Long generationNum, String colName, BigDecimal price) {
        this.clubId = clubId;
        this.generationNum = generationNum;
        this.colName = colName;
        this.price = price;
    }
}
