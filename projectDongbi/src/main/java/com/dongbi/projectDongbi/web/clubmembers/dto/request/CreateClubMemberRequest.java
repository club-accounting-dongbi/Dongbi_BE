package com.dongbi.projectDongbi.web.clubmembers.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateClubMemberRequest(

        @Schema(description = "동아리 Id", nullable = false, example = "1")
        Long clubId,
        @Schema(description = "기수 번호", nullable = false, example = "1")
        Long generationNum,
        @Schema(description = "동아리원 이름", nullable = false, example = "김두한, 김삼한, 김사한")
        List<String> names
){
}
