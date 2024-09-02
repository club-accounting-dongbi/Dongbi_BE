package com.dongbi.projectDongbi.web.clubmembers;

import com.dongbi.projectDongbi.domain.clubmembers.service.ClubMemberService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.SearchClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ClubMember 컨트롤러", description = "ClubMember API입니다.")
@RequestMapping("/members")
public class ClubMemberController {

    private final ClubMemberService clubMemberService;

    @Operation(summary = "동아리원 추가", description = "동아리에 동아리원을 추가합니다.")
    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> createClubMember(@RequestBody CreateClubMemberRequest request){
        clubMemberService.createClubMember(request);
        return ResponseEntity.ok(ApiResponse.success());
    }


    @Operation(summary = "활동 동아리원 조회", description = "동아리 기수의 활동 동아리원을 조회합니다.")
    @GetMapping("/active/{clubId}/{generationNum}")
    public  ResponseEntity<ApiResponse<List<ClubMemberResponse>>> findActiveClubMembers(@PathVariable(name = "clubId") Long clubId, @PathVariable(name = "generationNum") Long generationNum){
        SearchClubMemberRequest request = new SearchClubMemberRequest(clubId, generationNum);
        List<ClubMemberResponse> result = clubMemberService.findGenerationClubMembers(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "동아리원 수정", description = "동아리원을 수정합니다.")
    @PatchMapping("update")
    public ResponseEntity<ApiResponse<Void>> updateClubMembers(@RequestBody List<UpdateClubMemberRequest> members){
        clubMemberService.updateClubMember(members);
        return ResponseEntity.ok(ApiResponse.success());
    }

}
