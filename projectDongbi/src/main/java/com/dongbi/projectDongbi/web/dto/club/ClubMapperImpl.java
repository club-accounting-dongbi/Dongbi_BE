package com.dongbi.projectDongbi.web.dto.club;

import com.dongbi.projectDongbi.domain.club.Club;
import org.springframework.stereotype.Component;

@Component
public class ClubMapperImpl implements ClubMapper{

    @Override
    public ClubResponseDto toResponseDto(Club club) {

        ClubResponseDto clubResponseDto = ClubResponseDto.builder()
                .id(club.getId())
                .name(club.getName())
                .build();

        return clubResponseDto;
    }
}
