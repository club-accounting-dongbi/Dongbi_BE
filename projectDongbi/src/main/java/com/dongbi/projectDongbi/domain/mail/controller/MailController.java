package com.dongbi.projectDongbi.domain.mail.controller;

import com.dongbi.projectDongbi.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailController {

    private final MailService mailService;
    private int number;

    @PostMapping("/send")
    public HashMap<String, Object> sendMail(@RequestParam String mail){
        HashMap<String, Object> map = new HashMap<>();

        try{
            number = mailService.sendMail(mail);
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        }catch (Exception e){
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }
        return map;
    }

    @GetMapping("/check")
    public ResponseEntity<?> mailCheck(@RequestParam String number){
        boolean isMatch = number.equals(String.valueOf(this.number));
        return ResponseEntity.ok(isMatch);
    }
}
