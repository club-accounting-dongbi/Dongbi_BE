package com.dongbi.projectDongbi.domain.clubmembers.repository;

import com.dongbi.projectDongbi.domain.clubmembers.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember,Long>, ClubMemberCustomRepository {

    List<ClubMember> findByGenerationId(Long id);

    Optional<ClubMember> findByName(String name);


}
