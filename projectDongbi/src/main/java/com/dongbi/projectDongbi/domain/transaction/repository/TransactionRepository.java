package com.dongbi.projectDongbi.domain.transaction.repository;

import com.dongbi.projectDongbi.domain.transaction.Transaction;
import com.dongbi.projectDongbi.web.transaction.dto.response.TransactionBankingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> , TransactionCustomRepository{


    List<TransactionBankingResponse> findTransactionByGenerationId(Long generationId);
}
