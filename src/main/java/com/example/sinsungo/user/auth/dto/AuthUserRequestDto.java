package com.example.sinsungo.user.auth.dto;

import com.example.sinsungo.user.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequestDto {
    private String username;

    private String password;

    private String adminToken;

    private UserRoleEnum user_role;

    private boolean admin = false;
}
