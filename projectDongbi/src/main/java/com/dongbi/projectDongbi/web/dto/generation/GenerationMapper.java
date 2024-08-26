package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.generation.Generation;

import java.util.List;

public interface GenerationMapper {

    GenerationResponseDto toResponseDto(Generation generation);

    List<Long> findGenerationNum(Long clubId);

    List<String> getNames(Long clubId, Long generationNum);
}
