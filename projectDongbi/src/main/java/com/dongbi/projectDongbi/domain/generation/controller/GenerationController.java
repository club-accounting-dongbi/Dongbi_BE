package com.dongbi.projectDongbi.domain.generation.controller;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.service.GenerationService;
import com.dongbi.projectDongbi.web.dto.generation.GenerationMapper;
import com.dongbi.projectDongbi.web.dto.generation.GenerationRequestDto;
import com.dongbi.projectDongbi.web.dto.generation.GenerationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generations")
public class GenerationController {

    private final GenerationService generationService;
    private final GenerationMapper generationMapper;

    @PostMapping("")
    public ResponseEntity<GenerationResponseDto> createGeneration(@RequestBody GenerationRequestDto generationRequestDto){
        Generation generation = generationService.createGeneration(generationRequestDto);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/{generationNum}")
    public ResponseEntity<GenerationResponseDto> getGeneration(@PathVariable Long generationNum){
        Generation generation = generationService.getGenerationByGenerationNum(generationNum);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }
}
