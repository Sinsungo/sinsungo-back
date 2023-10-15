package com.example.sinsungo.user.dto;

import com.example.sinsungo.user.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private Long id;

    private String username;

    private String password;

    private String oauthProvider;

    private UserRoleEnum user_role;
}
