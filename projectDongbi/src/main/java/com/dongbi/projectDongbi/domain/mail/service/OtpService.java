package com.dongbi.projectDongbi.domain.mail.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    private final Map<String, String> otpMap = new ConcurrentHashMap<>();

    public String generateOtp(String email) {
        String otp = generateRandomOtp();
        otpMap.put(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpMap.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpMap.remove(email);
            return true;
        }
        return false;
    }

    private String generateRandomOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
