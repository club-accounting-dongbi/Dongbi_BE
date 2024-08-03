package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.SearchClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;

import java.util.List;

public interface ClubMemberCustomRepository {

    List<ClubMemberResponse> findByClubIdAndGenerationNum(SearchClubMemberRequest request);

    ClubMember findByClubIdAndName(Long clubId, String name);


    Long existsByMember(CreateClubMemberRequest request, String name);

}


