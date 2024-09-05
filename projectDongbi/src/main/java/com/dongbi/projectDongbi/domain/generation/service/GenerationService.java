package com.dongbi.projectDongbi.domain.generation.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.club.service.ClubService;
import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMemberRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.web.dto.generation.GenerationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenerationService {

    private final GenerationRepository generationRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final ClubService clubService;
    private final UserRepository userRepository;

    @Transactional
    public Generation createGeneration(Long userId, GenerationRequestDto requestDto){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Club club = user.getClub();

        Generation generation = new Generation().builder()
                .name(requestDto.getName())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .actFlag(true)
                .generationNum(requestDto.getGenerationNum())
                .club(club)
                .amount(new BigDecimal(0))
                .clubMembers(new ArrayList<>())
                .build();

        List<ClubMember> members = new ArrayList<>();
        for (String memberName : requestDto.getMemberNames()){
            ClubMember member = ClubMember.createClubMember(memberName, generation);
            members.add(member);
        }

        generation.getClubMembers().addAll(members);

        clubMemberRepository.saveAll(members);

        club.addGeneration(generation);

        return generationRepository.save(generation);
    }

    public Generation getGeneration(Long clubId, Long generationNum){
        return generationRepository.findByClubMemberIdAndGenerationNum(clubId, generationNum);
    }

    public Generation getGenerationByGenerationNum(Long userId, Long generationNum) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        return user.getClub().getGenerations().stream()
                .filter(generation -> generation.getGenerationNum().equals(generationNum))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Generation을 찾을 수 없습니다."));
    }

    @Transactional
    public Generation updateEndDate(Long userId, Long generationNum, LocalDate endDate){
        Generation generation = getGenerationByGenerationNum(userId, generationNum);

        if (endDate == null || endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("유효하지 않은 종료 날짜입니다.");
        }
        generation.updateEndDate(endDate);
        return generationRepository.save(generation);
    }

    public boolean isDuplicate(Long generationNum){
        return generationRepository.existsByGenerationNum(generationNum);
    }
}
