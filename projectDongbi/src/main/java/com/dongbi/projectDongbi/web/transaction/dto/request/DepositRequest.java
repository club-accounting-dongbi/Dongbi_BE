package com.dongbi.projectDongbi.web.transaction.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {

    @Schema(description = "동아리 Id", nullable = false, example = "1")
    private Long clubId;
    @Schema(description = "동아리 기수 번호", nullable = false, example = "1")
    private Long generationNum;
    @Schema(description = "활동 컬럼 명", nullable = false, example = "동아리 회비")
    private String colName;
    @Schema(description = "금액", nullable = false, example = "10000")
    private BigDecimal price;
    @Schema(description = "동아리원 명", nullable = false, example = "김두한")
    private List<String> names;


}
