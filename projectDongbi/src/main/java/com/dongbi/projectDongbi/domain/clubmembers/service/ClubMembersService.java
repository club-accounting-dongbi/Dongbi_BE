package com.dongbi.projectDongbi.domain.clubmembers.service;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMembers;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMembersRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.web.clubmembers.dto.CreateClubMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubMembersService {

    private final ClubMembersRepository clubMembersRepository;
    private final GenerationRepository generationRepository;

    public void createClubMember(CreateClubMemberRequest member, Long generationId){

        Generation generation = generationRepository.findById(generationId)
                .orElseThrow(() -> new RuntimeException("generation not found with generationId: " + generationId));

        ClubMembers clubMembers = ClubMembers.builder()
                .generation(generation)
                .name(member.name())
                .build();
        clubMembersRepository.save(clubMembers);
    }

    public void deleteClubMembers(Long clubmemberId) {
        ClubMembers findMember = clubMembersRepository.findById(clubmemberId)
                .orElseThrow(() -> new RuntimeException("Club member not found with clubmemberId: " + clubmemberId));
        findMember.deleteClubMembers();
        clubMembersRepository.save(findMember);
    }
   

}
