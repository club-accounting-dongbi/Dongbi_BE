package com.dongbi.projectDongbi.security.auth.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.web.dto.user.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User signupUser(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        String encPassword = bCryptPasswordEncoder.encode(signupRequestDto.getPassword());

        Club club = Club.builder()
                .name(signupRequestDto.getClubname())
                .build();

        User user = User.builder()
                .role("ROLE_USER")
                .email(signupRequestDto.getEmail())
                .password(encPassword)
                .club(club)
                .build();

        return userRepository.save(user);
    }
}
