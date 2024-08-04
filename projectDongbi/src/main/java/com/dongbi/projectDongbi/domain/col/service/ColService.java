package com.dongbi.projectDongbi.domain.col.service;

import com.dongbi.projectDongbi.domain.col.Col;
import com.dongbi.projectDongbi.domain.col.repository.ColRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.service.GenerationService;
import com.dongbi.projectDongbi.web.col.dto.request.ColGenerateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColService {

    private final ColRepository colRepository;
    private final GenerationService generationService;

    public void generateCol(List<ColGenerateRequest> requests) {
        for (ColGenerateRequest request : requests) {
            Generation generation = generationService.getGeneration(request.getClubId(), request.getGenerationNum());
            Col col = Col.createCol(request.getColName(), request.getPrice(),generation);
            colRepository.save(col);
        }

    }
}
