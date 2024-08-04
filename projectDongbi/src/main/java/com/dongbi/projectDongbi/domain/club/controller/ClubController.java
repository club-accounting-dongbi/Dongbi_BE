package com.dongbi.projectDongbi.domain.club.controller;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.club.service.ClubService;
import com.dongbi.projectDongbi.web.dto.club.ClubMapper;
import com.dongbi.projectDongbi.web.dto.club.ClubRequestDto;
import com.dongbi.projectDongbi.web.dto.club.ClubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;
    private final ClubMapper clubMapper;

    @PatchMapping("/name")
    public ResponseEntity<ClubResponseDto> changeName(@RequestBody ClubRequestDto requestDto) {
        Club club = clubService.changeClubname(requestDto);
        ClubResponseDto responseDto = clubMapper.toResponseDto(club);

        return ResponseEntity.ok(responseDto);
    }
}
