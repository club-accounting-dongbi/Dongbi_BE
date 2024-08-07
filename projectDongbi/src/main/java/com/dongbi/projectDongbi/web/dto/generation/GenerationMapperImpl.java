package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.generation.Generation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerationMapperImpl implements GenerationMapper{

    @Override
    public GenerationResponseDto toResponseDto(Generation generation) {
        GenerationResponseDto responseDto = new GenerationResponseDto().builder()
                .id(generation.getId())
                .name(generation.getName())
                .startDate(generation.getStartDate())
                .endDate(generation.getEndDate())
                .memberName(generation.getMemberNames())
                .build();
        return responseDto;
    }
}
