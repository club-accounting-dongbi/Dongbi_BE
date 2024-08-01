package com.dongbi.projectDongbi.domain.paid.service;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.domain.paid.repository.PaidRepository;
import com.dongbi.projectDongbi.domain.transaction.service.TransactionService;
import com.dongbi.projectDongbi.web.paid.dto.PaidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaidService {

    private final PaidRepository paidRepository;
    private final TransactionService transactionService;



    //Todo: 자동화 시스템 ( 입출금시 transaction 생성)
    @Transactional
    public void updatePaid(ClubMember clubMember, Long generationNum, List<PaidRequest> paidList) {
        List<Paid> list = paidRepository.findByPaidInColNameAndClubMemberId(clubMember.getId(), generationNum, paidList);
        Map<String, Boolean> paidMap = paidList.stream()
                .collect(Collectors.toMap(PaidRequest::getColName, PaidRequest::getPaid));
        
        for (Paid paid : list) {
            paid.updatePaid(clubMember,paidMap.get(paid.getCol().getColName()));
            if(paid.getPaid()){
                transactionService.autoCreateDeposit(clubMember,paid);
            }else{
                transactionService.autoDeleteDeposit(clubMember,paid);
            }

        }


    }
}
