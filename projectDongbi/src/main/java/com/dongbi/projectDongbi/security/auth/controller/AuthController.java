package com.dongbi.projectDongbi.security.auth.controller;

import com.dongbi.projectDongbi.security.auth.service.AuthService;
import com.dongbi.projectDongbi.web.dto.user.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> join(@RequestBody SignupRequestDto signupRequestDto) {
        try {
            authService.signupUser(signupRequestDto);
            return ResponseEntity.ok(Map.of("message", "회원 가입 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
