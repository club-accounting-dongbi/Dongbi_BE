package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubMemberCustomRepository {

    List<ClubMemberResponse> findByClubIdAndGenerationNum(@Param("clubId") Long clubId, @Param("generationNum") Long generationNum);

    ClubMember findByClubIdAndName(Long clubId, String name);


    Long existsByMember(CreateClubMemberRequest request);

}


