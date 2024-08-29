package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.domain.generation.Generation;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class GenerationAndClubMemberResDto {

    private Long id;
    private String name;
    private Long generationNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private boolean actFlag;

    // Generation 엔티티를 GenerationResponse로 변환하는 메서드
    public static GenerationAndClubMemberResDto fromEntity(Generation generation) {
        return GenerationAndClubMemberResDto.builder()
                .id(generation.getId())
                .name(generation.getName())
                .generationNum(generation.getGenerationNum())
                .startDate(generation.getStartDate())
                .endDate(generation.getEndDate())
                .amount(generation.getAmount())
                .actFlag(generation.isActFlag())
                .build();
    }
}
