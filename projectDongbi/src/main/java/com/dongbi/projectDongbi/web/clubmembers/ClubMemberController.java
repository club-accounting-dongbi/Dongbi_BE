package com.dongbi.projectDongbi.web.clubmembers;

import com.dongbi.projectDongbi.domain.clubmembers.service.ClubMemberService;
import com.dongbi.projectDongbi.global.common.response.ApiResponse;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.SearchClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ClubMemberController {

    private final ClubMemberService clubMemberService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> createClubMember(@RequestBody CreateClubMemberRequest request){
        clubMemberService.createClubMember(request);
        return ResponseEntity.ok(ApiResponse.success());
    }



    @GetMapping("/active/{clubId}/{generationNum}")
    public  ResponseEntity<ApiResponse<List<ClubMemberResponse>>> findActiveClubMembers(@PathVariable(name = "clubId") Long clubId, @PathVariable(name = "generationNum") Long generationNum){
        SearchClubMemberRequest request = new SearchClubMemberRequest(clubId, generationNum);
        List<ClubMemberResponse> result = clubMemberService.findGenerationClubMembers(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


    @PutMapping("update")
    public ResponseEntity<ApiResponse<Void>> updateClubMembers(@RequestBody List<UpdateClubMemberRequest> members){
        clubMemberService.updateClubMember(members);
        return ResponseEntity.ok(ApiResponse.success());
    }

}
