package com.example.sinsungo.user.OAuth.kakao;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import jakarta.servlet.http.HttpServletResponse;

public interface OAuthKakaoService {
    TokenResponseDto getKakaoAccessToken(String code, HttpServletResponse response);

    User createKakaoUser(String token);

    User findUserByEmail(String email);
}
