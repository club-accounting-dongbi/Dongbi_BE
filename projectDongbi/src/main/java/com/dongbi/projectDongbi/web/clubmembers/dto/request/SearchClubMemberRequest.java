package com.dongbi.projectDongbi.web.clubmembers.dto.request;

import lombok.Getter;

@Getter
public class SearchClubMemberRequest {
    private Long clubId;
    private Long generationNum;

    public SearchClubMemberRequest(Long clubId, Long generationNum) {
        this.clubId = clubId;
        this.generationNum = generationNum;
    }
}
