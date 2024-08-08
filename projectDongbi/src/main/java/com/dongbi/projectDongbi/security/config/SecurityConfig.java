package com.dongbi.projectDongbi.security.config;

import com.dongbi.projectDongbi.security.auth.service.AuthService;
import com.dongbi.projectDongbi.security.config.jwt.JwtLoginFilter;
import com.dongbi.projectDongbi.security.config.jwt.JwtAuthorizationFilter;
import com.dongbi.projectDongbi.domain.refreshToken.repository.RefreshTokenRepository;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.security.config.jwt.JwtLogoutFilter;
import com.dongbi.projectDongbi.security.config.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        http

                .csrf(csrf -> csrf.disable())
                .addFilter(corsFilter) //@CrossOrigin(인증X), 시큐리티 필터에 등록 인증

                // 스프링 시큐리티가 세션 생성 못하게 함 (토큰 방식으로 하기 위해 (JWT))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/auth/**", "/email/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(new JwtLoginFilter(authenticationManager, refreshTokenRepository, jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil, userRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtLogoutFilter(authService), LogoutFilter.class);
        return http.build();
    }
}