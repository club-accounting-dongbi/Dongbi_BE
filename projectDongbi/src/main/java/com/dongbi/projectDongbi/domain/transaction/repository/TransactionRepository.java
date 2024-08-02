package com.dongbi.projectDongbi.domain.transaction.repository;

import com.dongbi.projectDongbi.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> , TransactionCustomRepository{



}
