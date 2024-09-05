package com.dongbi.projectDongbi.domain.transaction.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import com.dongbi.projectDongbi.domain.transaction.Transaction;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import com.dongbi.projectDongbi.web.transaction.dto.request.TransactionConditionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionCustomRepository {
    Page<TransactionBankingResponse> findTransaction(TransactionConditionRequest request, Pageable pageable);

    Transaction findDepositTransaction(ClubMember clubMember, Paid paid);

    List<TransactionBankingResponse> findAllTransaction(Long clubId,Long generationNum);

}
