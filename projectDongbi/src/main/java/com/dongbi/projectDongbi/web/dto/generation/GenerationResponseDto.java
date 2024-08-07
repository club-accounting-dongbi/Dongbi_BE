package com.dongbi.projectDongbi.web.dto.generation;

import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerationResponseDto {

    private Long id;
    private String name;
    private Long generationNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ClubMemberResponse> members;
    private BigDecimal amount;
    private boolean actFlag;
}
