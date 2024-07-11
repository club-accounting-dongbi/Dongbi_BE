package com.dongbi.projectDongbi.web.clubmembers;

import com.dongbi.projectDongbi.domain.clubmembers.service.ClubMembersService;
import com.dongbi.projectDongbi.web.clubmembers.dto.CreateClubMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClubMembersController {

    private final ClubMembersService clubMembersService;

    @PostMapping("members/new/{generationId}")
    public ResponseEntity<Void> createMember(@RequestBody CreateClubMemberRequest member, @PathVariable Long generationId){
        clubMembersService.createClubMember(member, generationId);

        return ResponseEntity.ok()
                .build();
    }

}
