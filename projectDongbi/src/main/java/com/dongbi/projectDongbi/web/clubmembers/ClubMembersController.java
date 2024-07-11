package com.dongbi.projectDongbi.web.clubmembers;

import com.dongbi.projectDongbi.domain.clubmembers.service.ClubMembersService;
import com.dongbi.projectDongbi.web.clubmembers.dto.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.UpdateClubMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ClubMembersController {

    private final ClubMembersService clubMembersService;

    @PostMapping("/new/{generationId}")
    public ResponseEntity<Void> createMember(@RequestBody CreateClubMemberRequest member, @PathVariable Long generationId){
        clubMembersService.createClubMember(member, generationId);
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/clubmember/{memberId}")
    public ResponseEntity<Void> deleteClubMember(@PathVariable Long memberId){
            clubMembersService.deleteClubMembers(memberId);

        return ResponseEntity.ok().build();
    }

}
