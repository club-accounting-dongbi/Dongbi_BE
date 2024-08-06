package com.dongbi.projectDongbi.security.config.jwt;

import com.dongbi.projectDongbi.security.config.auth.PrincipalDetails;
import com.dongbi.projectDongbi.domain.refreshToken.RefreshToken;
import com.dongbi.projectDongbi.domain.refreshToken.repository.RefreshTokenRepository;
import com.dongbi.projectDongbi.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

//스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있는데
// /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 가 동작.

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter (AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/auth/login"); // URL 경로 설정
    }

    // /auth/login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도 중");

        //username, password 받아서
        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            //PrincipalDetailsService의 loadUserByUsername() 함수 실행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            //return 이유 ) 권한 관리를 security 가 대신 해주기 때문에 편하라고 하는 것.
            //JWT 토큰을 사용하는데 굳이 세션을 만들 필요가 없지만 권한 처리 때문에 넣음
            return authentication;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //attemptAuthentication 실행 후 인증이 되었을 때 실행
    //JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //JWT 토큰 생성
        //RSA 방식 X Hash암호 방식
        String jwtToken = jwtUtil.createJwtToken(principalDetails.getUser());
        String refreshToken = jwtUtil.createRefreshToken(principalDetails.getUser());

        RefreshToken token = RefreshToken.builder()
                .userId(principalDetails.getUser().getId())
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(token);

        response.addHeader("Authorization", "Bearer "+jwtToken);
        response.addHeader("Refresh-Token", refreshToken);
    }
}