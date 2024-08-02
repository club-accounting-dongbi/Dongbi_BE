package com.dongbi.projectDongbi.domain.user.controller;

import com.dongbi.projectDongbi.config.auth.PrincipalDetails;
import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.service.UserService;
import com.dongbi.projectDongbi.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> clubsLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            User user = userService.loginUser(loginRequestDto);

            Map<String, Object> response = new HashMap<>();
            UserResponseDto userResponseDto = userMapper.toResponseDto(user);
            response.put("user", userResponseDto);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> join(@RequestBody SignupRequestDto signupRequestDto) {
        try {
            userService.signupUser(signupRequestDto);
            return ResponseEntity.ok(Map.of("message", "회원 가입 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updatePassword(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        userService.updatePassword(principalDetails.getUser().getId(), updatePasswordRequestDto);

        try {
            userService.updatePassword(principalDetails.getUser().getId(), updatePasswordRequestDto);
            return ResponseEntity.ok(Map.of("message", "비밀번호 변경 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(Map.of("message", e.getMessage()));
        }
    }

}