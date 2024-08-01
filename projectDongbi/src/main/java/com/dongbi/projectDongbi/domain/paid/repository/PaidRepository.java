package com.dongbi.projectDongbi.domain.paid.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import com.dongbi.projectDongbi.domain.paid.Paid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaidRepository extends JpaRepository<Paid,Long> , PaidCustomRepository{
    List<Paid> findByClubMember(ClubMember clubMember);
}
