package com.dongbi.projectDongbi.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurity {
    private String email;
    private String role;
    private String password;
    private String clubname;
}
