package com.dongbi.projectDongbi.web.clubmembers.dto.request;

import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateClubMemberRequest {
    private Long clubId;
    private String name;
    private List<PaidRequest> paid;
    private Boolean delFlag;
    private Boolean actFlag;
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