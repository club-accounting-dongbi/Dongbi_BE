package com.dongbi.projectDongbi.web.dto.user;

import com.dongbi.projectDongbi.domain.user.User;

public interface UserMapper {
    UserResponseDto toResponseDto(User user);
}
