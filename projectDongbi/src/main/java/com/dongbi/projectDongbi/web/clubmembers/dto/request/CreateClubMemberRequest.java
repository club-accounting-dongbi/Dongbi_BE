package com.dongbi.projectDongbi.web.clubmembers.dto.request;

import java.util.List;

public record CreateClubMemberRequest(
        Long clubId,
        Long generationNum,
        List<String> names
){
}
