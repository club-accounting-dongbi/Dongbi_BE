package com.dongbi.projectDongbi.web.col;

import com.dongbi.projectDongbi.domain.col.service.ColService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.web.col.dto.request.ColGenerateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "컬럼 api 컨트롤러", description = "컬럼 api 컨트롤러" )
@RestController
@RequiredArgsConstructor
@RequestMapping("/col")
public class ColController {

    private final ColService colService;

    @Operation(summary = "컬럼 추가", description = "컬럼을 추가합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> generateCol(@RequestBody List<ColGenerateRequest> requests){
        colService.generateCol(requests);

        return ResponseEntity.ok(ApiResponse.success());
    }

}
