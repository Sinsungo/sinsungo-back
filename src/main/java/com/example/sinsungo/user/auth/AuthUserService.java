package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.auth.dto.AuthUserRequestDto;

public interface AuthUserService {
    ApiResponseDto signup(AuthUserRequestDto requestDto);

    boolean checkAdmin(String adminToken);
}
