package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.auth.dto.SignInRequestDto;
import com.example.sinsungo.user.auth.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthUserService {
    ApiResponseDto signup(SignUpRequestDto requestDto);

    ApiResponseDto signin(SignInRequestDto requestDto, HttpServletResponse response);

    boolean checkAdmin(String adminToken);

    User findUserByUserName(String username);
}
