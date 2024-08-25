package com.dongbi.projectDongbi.web.dto.generation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerationFindMembersRequest {
    private Long clubId;
    private Long generationNum;
}
