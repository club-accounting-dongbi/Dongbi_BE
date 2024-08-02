package com.dongbi.projectDongbi.domain.club.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.club.repository.ClubRepository;
import com.dongbi.projectDongbi.web.dto.club.ClubRequestDto;
import com.dongbi.projectDongbi.web.dto.club.ClubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubServiceImpl implements ClubService{

    private final ClubRepository clubRepository;

    @Override
    public Club findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException(clubId + "에 해당하는 클럽이 없습니다."));
        return club;
    }

    @Override
    @Transactional
    public Club changeClubname(ClubRequestDto requestDto) {
        System.out.println(requestDto.getClubName());
        Club club = findClubById(requestDto.getId());
        club.updateName(requestDto.getClubName());

        return club;
    }
}
