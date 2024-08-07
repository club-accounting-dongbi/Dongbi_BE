package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.generation.Generation;

public interface GenerationMapper {

    GenerationResponseDto toResponseDto(Generation generation);
}
