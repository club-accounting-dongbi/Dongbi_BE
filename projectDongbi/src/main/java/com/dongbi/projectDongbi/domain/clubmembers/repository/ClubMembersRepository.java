package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMembersRepository extends JpaRepository<ClubMembers,Long> {
}
