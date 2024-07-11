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
@RequiredArgsConstructor
public class ClubMembersService {

    private final ClubMembersRepository clubMembersRepository;
    private final GenerationRepository generationRepository;

    public void createClubMember(CreateClubMemberRequest member, Long generationId){

        Generation generation = generationRepository.findById(generationId)
                .orElseThrow(() -> new NullPointerException("해당기수가 없습니다."));

        ClubMembers clubMembers = ClubMembers.builder()
                .generation(generation)
                .name(member.name())
                .build();
        clubMembersRepository.save(clubMembers);
    }

    
}
