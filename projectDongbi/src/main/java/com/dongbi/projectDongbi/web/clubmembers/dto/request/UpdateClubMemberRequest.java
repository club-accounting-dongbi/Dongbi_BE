package com.dongbi.projectDongbi.web.clubmembers.dto.request;

import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateClubMemberRequest {

    @Schema(description = "동아리 Id", nullable = false, example = "1")
    private Long clubId;
    @Schema(description = "동아리원 이름", nullable = false, example = "김두한")
    private String name;
    @Schema(description = "회비 지불 여부 List", nullable = true, example = "[0,0,0,1,1]")
    private List<PaidRequest> paid;
    @Schema(description = "삭제 여부", nullable = true, example = "1")
    private Boolean delFlag;
    @Schema(description = "활동 여부", nullable = true, example = "1")
    private Boolean actFlag;
    @Schema(description = "동아리 기수 번호", nullable = false, example = "1")
    private Long generationNum;

    public UpdateClubMemberRequest(Long clubId, String name, List<PaidRequest> paid, Boolean del_flag, Boolean act_flag,Long generationNum ) {
        this.clubId = clubId;
        this.name = name;
        this.paid = paid;
        this.delFlag = del_flag;
        this.actFlag = act_flag;
        this.generationNum = generationNum;
    }


}