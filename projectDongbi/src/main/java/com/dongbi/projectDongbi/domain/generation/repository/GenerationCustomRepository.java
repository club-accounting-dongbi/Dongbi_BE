package com.dongbi.projectDongbi.domain.generation.repository;

import com.dongbi.projectDongbi.domain.generation.Generation;

public interface GenerationCustomRepository {


    Generation findByClubMemberIdAndGenerationNum(Long clubId, Long generationNum);

}
