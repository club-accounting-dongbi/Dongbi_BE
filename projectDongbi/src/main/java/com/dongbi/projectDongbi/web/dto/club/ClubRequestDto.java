package com.dongbi.projectDongbi.web.dto.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequestDto {
    private Long id;
    private String clubname;
}
