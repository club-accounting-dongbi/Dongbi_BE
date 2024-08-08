package com.dongbi.projectDongbi.domain.user.service;

import com.dongbi.projectDongbi.domain.user.User;
import com.dongbi.projectDongbi.domain.user.repository.UserRepository;
import com.dongbi.projectDongbi.web.dto.user.UpdatePasswordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "에 해당하는 유저가 없습니다."));
        return user;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updatePassword(Long userId, UpdatePasswordRequestDto updatePasswordRequestDto){
        User user = findById(userId);
        if(!bCryptPasswordEncoder.matches(updatePasswordRequestDto.getOldPassword(), user.getPassword())){
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
        String encPassword = bCryptPasswordEncoder.encode(updatePasswordRequestDto.getNewPassword());
        user.setPassword(encPassword);
        userRepository.save(user);
    }

}
