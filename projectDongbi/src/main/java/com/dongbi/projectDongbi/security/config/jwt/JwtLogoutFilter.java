package com.dongbi.projectDongbi.security.config.jwt;

import com.dongbi.projectDongbi.security.auth.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtLogoutFilter extends GenericFilterBean {

    private final AuthService authService;

    public JwtLogoutFilter(AuthService authService){
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getRequestURI().equals("/auth/logout") && httpRequest.getMethod().equalsIgnoreCase("POST")) {
            String refreshToken = getRefreshTokenFromCookies(httpRequest.getCookies());

            if (refreshToken != null){
                authService.logout(refreshToken);

                // 쿠키 만료 처리
                Cookie cookie = new Cookie("refresh", null);
                cookie.setPath("/"); // 쿠키의 경로를 설정
                cookie.setMaxAge(0); // 쿠키 만료
                httpResponse.addCookie(cookie); // 쿠키를 응답에 추가

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if(auth != null){
                    new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, auth);
                }
                httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }else{
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(httpRequest, httpResponse);
    }

    private String getRefreshTokenFromCookies(Cookie[] cookies){
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("refresh")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
