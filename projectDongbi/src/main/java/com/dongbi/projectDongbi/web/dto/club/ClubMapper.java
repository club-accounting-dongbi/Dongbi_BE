package com.dongbi.projectDongbi.web.dto.club;

import com.dongbi.projectDongbi.domain.club.Club;

public interface ClubMapper {

    ClubResponseDto toResponseDto(Club club);
}
