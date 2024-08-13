package com.dongbi.projectDongbi.domain.generation.repository;

import com.dongbi.projectDongbi.domain.generation.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long>, GenerationCustomRepository{

    Generation findGenerationByClubIdAndGenerationNum(Long clubId, Long generationNum);
    Generation findGenerationByGenerationNum(Long generationNum);
    boolean existsByGenerationNum(Long generationNum);
}
