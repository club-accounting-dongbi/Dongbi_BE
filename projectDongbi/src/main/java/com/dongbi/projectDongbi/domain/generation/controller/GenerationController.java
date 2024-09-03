package com.dongbi.projectDongbi.domain.generation.controller;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.service.GenerationService;
import com.dongbi.projectDongbi.security.config.jwt.JwtUtil;
import com.dongbi.projectDongbi.web.dto.generation.GenerationMapper;
import com.dongbi.projectDongbi.web.dto.generation.GenerationRequestDto;
import com.dongbi.projectDongbi.web.dto.generation.GenerationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generations")
public class GenerationController {

    private final GenerationService generationService;
    private final GenerationMapper generationMapper;
    private final JwtUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<GenerationResponseDto> createGeneration(@RequestHeader("Authorization") String authorization, @RequestBody GenerationRequestDto generationRequestDto){
        Long userId = jwtUtil.extractUserId(authorization);
        Generation generation = generationService.createGeneration(userId, generationRequestDto);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/check/{generationNum}")
    public ResponseEntity<?> checkGenerationNum(@PathVariable Long generationNum){
        return ResponseEntity.ok(generationService.isDuplicate(generationNum));
    }

    @GetMapping("/{generationNum}")
    public ResponseEntity<GenerationResponseDto> getGeneration(@RequestHeader("Authorization") String authorization, @PathVariable Long generationNum){
        Long userId = jwtUtil.extractUserId(authorization);
        Generation generation = generationService.getGenerationByGenerationNum(userId, generationNum);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }

    @PatchMapping("/term/{generationNum}")
    public ResponseEntity<GenerationResponseDto> updateEndDate(@RequestHeader("Authorization") String authorization, @PathVariable Long generationNum, @RequestBody LocalDate endDate){
        Long userId = jwtUtil.extractUserId(authorization);
        System.out.println("fjsdfbgasgkbfskjglnaskjgnafskngkjlfl");
        Generation generation = generationService.updateEndDate(userId, generationNum, endDate);
        System.out.println("endDate = " + endDate);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }
}
