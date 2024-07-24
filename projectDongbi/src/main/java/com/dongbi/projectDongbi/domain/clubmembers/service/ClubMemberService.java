package com.dongbi.projectDongbi.domain.clubmembers.service;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMemberRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.domain.paid.service.PaidService;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final GenerationRepository generationRepository;
    private final PaidService paidService;

    @Transactional
    public void createClubMember(CreateClubMemberRequest request){

        Generation generation = generationRepository.findGenerationByClubIdAndGenerationNum(request.clubId(),request.generationNum());
        if(request.names().isEmpty()) throw new RuntimeException("이름을 적어주세요.");
        if(clubMemberRepository.existsByMember(request) != request.names().size()) throw new DuplicateRequestException("중복된 이름이 있습니다.");

        for (String name : request.names()) {
            ClubMember clubMember = ClubMember.builder()
                    .generation(generation)
                    .name(name)
                    .build();
            clubMemberRepository.save(clubMember);
        }
    }




    @Transactional
    public void updateClubMember(List<UpdateClubMemberRequest> requests) {
        for (UpdateClubMemberRequest request : requests) {
            ClubMember clubMember = clubMemberRepository.findByClubIdAndName(request.getClubId(),request.getName());
            if(clubMember != null){
                clubMember.updateClubMember(request);
                if(!request.getPaid().isEmpty()){
                    paidService.updatePaid(clubMember,request.getGenerationNum(),request.getPaid());
                }
            }
        }

    }

    public List<ClubMemberResponse> findGenerationClubMembers(Long clubId,Long generationNum) {
        return clubMemberRepository.findByClubIdAndGenerationNum(clubId,generationNum);
    }


}
