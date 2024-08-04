package com.dongbi.projectDongbi.domain.clubmembers.service;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMemberRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.domain.paid.service.PaidService;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.CreateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.SearchClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.request.UpdateClubMemberRequest;
import com.dongbi.projectDongbi.web.clubmembers.dto.response.ClubMemberResponse;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final GenerationRepository generationRepository;
    private final PaidService paidService;

    @Transactional
    public void createClubMember(CreateClubMemberRequest request){

        Generation generation = generationRepository.findGenerationByClubIdAndGenerationNum(request.clubId(),request.generationNum());
       if(request.names().isEmpty()) throw new RuntimeException("이름을 적어주세요.");


        for (String name : request.names()) {
            if(clubMemberRepository.existsByMember(request, name) > 0) throw new DuplicateRequestException("중복된 이름이 있습니다.");
            ClubMember clubMember = ClubMember.createClubMember(name,generation);
            clubMemberRepository.save(clubMember);
        }
    }




    @Transactional
    public void updateClubMember(List<UpdateClubMemberRequest> requests) {
        for (UpdateClubMemberRequest request : requests) {
            ClubMember clubMember = clubMemberRepository.findByClubIdAndName(request.getClubId(),request.getName());
            if(clubMember != null){
                clubMember.updateClubMember(request);
                if(request.getPaid() != null ){
                    paidService.updatePaid(clubMember,request.getGenerationNum(),request.getPaid());
                }
            }
        }

    }

    public List<ClubMemberResponse> findGenerationClubMembers(SearchClubMemberRequest request) {
      if(request.getClubId() == null || request.getGenerationNum() == null){
            throw  new NoSuchElementException("클럽Id나 기수번호가 잘못되었습니다.");
        }
        return clubMemberRepository.findByClubIdAndGenerationNum(request);
    }

}
