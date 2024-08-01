package com.dongbi.projectDongbi.domain.user.service;

import com.dongbi.projectDongbi.config.auth.PrincipalDetails;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.web.dto.user.LoginRequestDto;
import com.dongbi.projectDongbi.web.dto.user.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signupUser(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        String encPassword = bCryptPasswordEncoder.encode(signupRequestDto.getPassword());

        User user = User.builder()
                .role("ROLE_USER")
                .email(signupRequestDto.getEmail())
                .password(encPassword)
                .build();

        return userRepository.save(user);
    }

    public User loginUser(LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getUser();
    }
}
