package com.dongbi.projectDongbi.domain.club.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.web.dto.club.ClubRequestDto;

public interface ClubService {

    Club findClubById(Long clubId);
    Club changeClubname(ClubRequestDto requestDto);
}
