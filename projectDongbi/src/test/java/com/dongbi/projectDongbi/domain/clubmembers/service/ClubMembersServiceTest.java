package com.dongbi.projectDongbi.domain.clubmembers.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.clubmembers.ClubMembers;
import com.dongbi.projectDongbi.domain.clubmembers.repository.ClubMembersRepository;
import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.generation.repository.GenerationRepository;
import com.dongbi.projectDongbi.web.clubmembers.dto.CreateClubMemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClubMembersServiceTest {


    @Autowired
    ClubMembersService clubMembersService;
    @Autowired
    ClubMembersRepository clubMembersRepository;
    @Autowired
    GenerationRepository generationRepository;

    static Generation generation;
        static ClubMembers clubMembers;
        static CreateClubMemberRequest clubMemberRequest;
    static ClubMembers saveMember;
    @BeforeEach
    void init(){
        generation = new Generation(1L,"test");
        clubMemberRequest = new CreateClubMemberRequest("cr");
        clubMembers = new ClubMembers("exper",generation);
        saveMember = clubMembersRepository.save(clubMembers);
        ClubMembers clubMembers1 = clubMembersRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Club member not found with id: "));

    }

    @Test
    @Transactional
    void createClubMember() {
        ClubMembers findMember = clubMembersService.findById(clubMembers.getId());
        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void deleteClubMembers() {
    }

    @Test
    void updateClubMember() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}