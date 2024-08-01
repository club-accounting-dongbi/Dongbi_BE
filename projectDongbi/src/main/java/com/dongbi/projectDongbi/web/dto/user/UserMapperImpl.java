package com.dongbi.projectDongbi.web.dto.user;

import com.dongbi.projectDongbi.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserResponseDto toResponseDto(User user){
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        return userResponseDto;
    }
}
