package com.dongbi.projectDongbi.domain.mail.controller;

import com.dongbi.projectDongbi.domain.mail.service.MailService;
import com.dongbi.projectDongbi.domain.mail.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailController {

    private final MailService mailService;
    private final OtpService otpService;

    @PostMapping("/send")
    public HashMap<String, Object> sendMail(@RequestParam String mail){
        HashMap<String, Object> map = new HashMap<>();

        try{
            String otp = otpService.generateOtp(mail);
            boolean success = mailService.sendMail(mail, otp);

            map.put("success", success);
            map.put("number", otp);
        }catch (Exception e){
            map.put("success", false);
            map.put("error", e.getMessage());
        }
        return map;
    }

    @GetMapping("/check")
    public ResponseEntity<?> mailCheck(@RequestParam String mail, @RequestParam String otp){
        boolean isMatch = otpService.verifyOtp(mail, otp);
        return ResponseEntity.ok(isMatch);
    }
}
