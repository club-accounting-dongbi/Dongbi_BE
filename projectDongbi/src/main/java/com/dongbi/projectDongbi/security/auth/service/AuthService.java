package com.dongbi.projectDongbi.security.auth.service;

import com.dongbi.projectDongbi.domain.club.Club;
import com.dongbi.projectDongbi.domain.refreshToken.RefreshToken;
import com.dongbi.projectDongbi.domain.refreshToken.repository.RefreshTokenRepository;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.security.config.jwt.JwtUtil;
import com.dongbi.projectDongbi.web.dto.auth.AuthResponseDto;
import com.dongbi.projectDongbi.web.dto.user.SignupRequestDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
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

    @Transactional
    public AuthResponseDto refresh(HttpServletRequest request, HttpServletResponse response){
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refreshToken = cookie.getValue();
                break;
            }
        }

        if(refreshToken == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        try {
            jwtUtil.isExpired(refreshToken);
        }
        catch (ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if(!refreshTokenRepository.existsByToken(refreshToken)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        refreshTokenRepository.deleteByToken(refreshToken);

        String email = jwtUtil.getEmail(refreshToken);
        if (email == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        // 데이터베이스에서 사용자 조회
        User user = userRepository.findByEmail(email);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        String jwtToken = jwtUtil.createJwtToken(user);
        refreshToken = jwtUtil.createRefreshToken(user);

        RefreshToken token = RefreshToken.builder()
                .userId(user.getId())
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(token);

        response.setHeader("Authorization", "Bearer " + jwtToken);
        response.addCookie(jwtUtil.createCookie("refresh", refreshToken));

        return AuthResponseDto.builder()
                .id(user.getId())
                .accessToken(jwtToken)
                .build();
    }

}
