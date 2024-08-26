package com.dongbi.projectDongbi.domain.generation.controller;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.service.GenerationService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.web.dto.generation.GenerationMapper;
import com.dongbi.projectDongbi.web.dto.generation.GenerationRequestDto;
import com.dongbi.projectDongbi.web.dto.generation.GenerationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/check/{generationNum}")
    public ResponseEntity<?> checkGenerationNum(@PathVariable Long generationNum){
        return ResponseEntity.ok(generationService.isDuplicate(generationNum));
    }

    @GetMapping("/{generationNum}")
    public ResponseEntity<GenerationResponseDto> getGeneration(@PathVariable Long generationNum){
        Generation generation = generationService.getGenerationByGenerationNum(generationNum);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }

    @PatchMapping("/term/{generationNum}")
    public ResponseEntity<GenerationResponseDto> updateEndDate(@PathVariable Long generationNum, @RequestBody LocalDate endDate){
        System.out.println("fjsdfbgasgkbfskjglnaskjgnafskngkjlfl");
        Generation generation = generationService.updateEndDate(generationNum, endDate);
        System.out.println("endDate = " + endDate);
        GenerationResponseDto collect = generationMapper.toResponseDto(generation);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/num/{clubId}")
    public ResponseEntity<ApiResponse<List<Long>>> findGenerationNums(@PathVariable Long clubId){
        List<Long> result = generationMapper.findGenerationNum(clubId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/members/{clubId}/{generationNum}")
    public ResponseEntity<ApiResponse<List<String>>> findMembers(@PathVariable(name = "clubId") Long clubId,
                                                                 @PathVariable(name = "generationNum") Long generationNum){
        List<String> result = generationMapper.getNames(clubId,generationNum);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
