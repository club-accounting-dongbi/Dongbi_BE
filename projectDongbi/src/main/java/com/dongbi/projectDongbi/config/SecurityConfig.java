package com.dongbi.projectDongbi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity (securedEnabled = true, prePostEnabled = true)
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());

        //formLogin 방식 변경
//        http
//                .formLogin(withDefaults());
        http
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                );

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/magager/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
