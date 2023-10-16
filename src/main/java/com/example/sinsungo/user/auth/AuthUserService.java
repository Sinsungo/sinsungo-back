package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.auth.dto.SignUpRequestDto;

public interface AuthUserService {
    ApiResponseDto signup(SignUpRequestDto requestDto);

    boolean checkAdmin(String adminToken);
}
