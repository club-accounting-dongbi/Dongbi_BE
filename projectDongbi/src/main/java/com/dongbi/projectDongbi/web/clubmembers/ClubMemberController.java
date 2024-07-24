package com.dongbi.projectDongbi.web.clubmembers;

import com.dongbi.projectDongbi.domain.clubmembers.service.ClubMemberService;
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
    public ResponseEntity<Void> createClubMember(@RequestBody CreateClubMemberRequest request){
        clubMemberService.createClubMember(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{generationNum}")
    public  ResponseEntity<List<ClubMemberResponse>> findAllClubMembers(Long clubId,  @PathVariable Long generationNum){
        List<ClubMemberResponse> result = clubMemberService.findGenerationClubMembers(clubId, generationNum);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/active/{generationNum}")
    public  ResponseEntity<List<ClubMemberResponse>> findActiveClubMembers(Long clubId,  @PathVariable Long generationNum){
        List<ClubMemberResponse> result = clubMemberService.findGenerationClubMembers(clubId, generationNum);
        return ResponseEntity.ok(result);
    }


    @PutMapping("update")
    public ResponseEntity<Void> updateClubMembers(@RequestBody List<UpdateClubMemberRequest> members){
        clubMemberService.updateClubMember(members);
        return ResponseEntity.ok().build();
    }

}
