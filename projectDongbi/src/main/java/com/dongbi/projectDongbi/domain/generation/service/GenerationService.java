package com.dongbi.projectDongbi.domain.generation.service;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerationService {

    private final GenerationRepository generationRepository;

    public Generation getGeneration(Long clubId, Long generationNum){
        return generationRepository.findByClubMemberIdAndGenerationNum(clubId, generationNum);
    }
}
