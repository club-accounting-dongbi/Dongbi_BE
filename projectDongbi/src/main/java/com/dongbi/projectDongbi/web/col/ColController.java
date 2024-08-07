package com.dongbi.projectDongbi.web.col;

import com.dongbi.projectDongbi.domain.col.service.ColService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.web.col.dto.request.ColGenerateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/col")
public class ColController {

    private final ColService colService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> generateCol(@RequestBody List<ColGenerateRequest> requests){
        colService.generateCol(requests);

        return ResponseEntity.ok(ApiResponse.success());
    }

}
