package com.dongbi.projectDongbi.domain.generation.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.club.service.ClubService;
import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMemberRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.web.dto.generation.GenerationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerationService {

    private final GenerationRepository generationRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final ClubService clubService;

    @Transactional
    public Generation createGeneration(GenerationRequestDto requestDto){

        Club club = clubService.findClubById(requestDto.getClubId());

        Generation generation = new Generation().builder()
                .name(requestDto.getName())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .actFlag(true)
                .club(club)
                .clubMembers(new ArrayList<>())
                .build();

        List<ClubMember> members = new ArrayList<>();
        for (String memberName : requestDto.getMemberNames()){
            ClubMember member = ClubMember.createClubMember(memberName, generation);
            members.add(member);
        }

        generation.getClubMembers().addAll(members);

        generationRepository.save(generation);
        clubMemberRepository.saveAll(members);

        return generation;
    }

    public Generation getGeneration(Long clubId, Long generationNum){
        return generationRepository.findByClubMemberIdAndGenerationNum(clubId, generationNum);
    }

    public Generation getGenerationByGenerationNum(Long generationNum){
        System.out.println("generationNum = " + generationNum);
        System.out.println(generationRepository.findGenerationByGenerationNum(generationNum));
        return generationRepository.findGenerationByGenerationNum(generationNum);
    }
}
