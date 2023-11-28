package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.auth.dto.SignInRequestDto;
import com.example.sinsungo.user.auth.dto.SignUpRequestDto;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AuthUserService {
    ApiResponseDto signup(SignUpRequestDto requestDto);

    ApiResponseDto signIn(SignInRequestDto requestDto, HttpServletResponse response);

    boolean checkAdmin(String adminToken);

    @Transactional
    ApiResponseDto logout(User user, HttpServletRequest request);

    User findUserByUserName(String username);

    TokenResponseDto reissue(User user, HttpServletRequest request);
}
