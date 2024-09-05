package com.dongbi.projectDongbi.domain.transaction.service;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.service.GenerationService;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.domain.transaction.Transaction;
import com.dongbi.projectDongbi.domain.transaction.repository.TransactionRepository;
import com.dongbi.projectDongbi.web.transaction.dto.request.DepositRequest;
import com.dongbi.projectDongbi.web.transaction.dto.request.TransactionConditionRequest;
import com.dongbi.projectDongbi.web.transaction.dto.request.WithDrawalRequest;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final GenerationService generationService;



    @Transactional
    public Page<TransactionBankingResponse> getTransactionList(TransactionConditionRequest request, Pageable pageable) {

        Page<TransactionBankingResponse> transactionResponses = transactionRepository.findTransaction(request,pageable);

        return transactionResponses;
    }
    @Transactional
    public void createDeposit(DepositRequest request) {
        for(String name : request.getNames()){
        Generation generation = generationService.getGeneration(request.getClubId(), request.getGenerationNum());

        Transaction tr = Transaction.depositTransaction(generation,request.getPrice(),request.getColName(),name, generation.getAmount());;

        transactionRepository.save(tr);
        }

    }

    @Transactional
    public Long autoCreateDeposit(ClubMember clubMember, Paid paid){
        Transaction tr = Transaction.depositTransaction(clubMember.getGeneration(),paid.getCol().getPrice(), paid.getCol().getColName(), clubMember.getName(), clubMember.getGeneration().getAmount());
        transactionRepository.save(tr);
        return tr.getId();
    }

    @Transactional
    public Long autoDeleteDeposit(ClubMember clubMember, Paid paid){
        Transaction tr = Transaction.withdrawalTransaction(clubMember.getGeneration(),paid.getCol().getPrice(), LocalDate.now(), LocalTime.now(), clubMember.getName(),paid.getCol().getColName() + "입금 취소", clubMember.getGeneration().getAmount(), null);
        transactionRepository.save(tr);
        return tr.getId();
    }

    @Transactional
    public Long createWithdraw(WithDrawalRequest request,
                               String filePath) {
        Generation generation = generationService.getGeneration(request.getClubId(), request.getGenerationNum());

        Transaction tr = Transaction.withdrawalTransaction(generation, request.getPrice(), request.getOccurenceDate(), request.getOccurenceTime(), request.getPersonCharge(), request.getReason(), generation.getAmount(), filePath);
        Transaction saved = transactionRepository.save(tr);

        return saved.getId();
    }

    public List<TransactionBankingResponse> getAllTransactionList(Long clubId, Long generationNum) {
        return transactionRepository.findAllTransaction(clubId,generationNum);
    }
}
