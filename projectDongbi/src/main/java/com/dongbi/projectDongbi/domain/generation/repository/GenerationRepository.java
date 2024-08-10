package com.dongbi.projectDongbi.domain.generation.repository;

import com.dongbi.projectDongbi.domain.generation.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long>, GenerationCustomRepository{

    Generation findGenerationByClubIdAndGenerationNum(Long clubId, Long generationNum);
    Generation findGenerationByGenerationNum(Long generationNum);

    @Query("SELECT MAX(g.generationNum) FROM Generation g")
    Long findTopGenerationNum();

    List<Generation> findByEndDate(@RequestParam("endDate") LocalDate localDate);
}
