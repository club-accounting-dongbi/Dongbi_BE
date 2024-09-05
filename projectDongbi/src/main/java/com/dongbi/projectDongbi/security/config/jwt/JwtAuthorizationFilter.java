package com.dongbi.projectDongbi.security.config.jwt;

import com.dongbi.projectDongbi.security.config.auth.PrincipalDetails;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//시큐리티가 filter 를 가지고 있는데 그 필터 중에 BasicAuthenticationFilter 가 있다.
//권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있다.
//만약에 권한이나 인증이 필요한 주소가 아니라면 이 필터를 안 탄다.
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    //인증이나 권한이 필요한 주소 쵸엉이 있을 때 해당 필터를 타게 됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader = " + jwtHeader);

        //header가 있는 지 확인
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }

        // JWT 토큰 검증을 통해 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        try{
            jwtUtil.isExpired(jwtToken);
        }catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired");
            return;
        }

        String email = jwtUtil.getEmail(jwtToken);

        if(email != null){
            System.out.println("정상 실행");
            User userEntity = userRepository.findByEmail(email);

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

            //두 번째의 null 값은 본래 PASSWORD 입력 란인데 JWT 토큰 검증이 완료되었으므로 안 넣어도 된다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            //강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
