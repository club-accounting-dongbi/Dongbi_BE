package com.dongbi.projectDongbi.domain.user.controller;

import com.dongbi.projectDongbi.security.config.auth.PrincipalDetails;
import com.dongbi.projectDongbi.domain.user.service.UserService;
import com.dongbi.projectDongbi.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updatePassword(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){

        try {
            userService.updatePassword(principalDetails.getUser().getId(), updatePasswordRequestDto);
            return ResponseEntity.ok(Map.of("message", "비밀번호 변경 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(Map.of("message", e.getMessage()));
        }
    }

}