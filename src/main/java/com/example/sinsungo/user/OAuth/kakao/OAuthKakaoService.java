package com.example.sinsungo.user.OAuth.kakao;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import jakarta.servlet.http.HttpServletResponse;

public interface OAuthKakaoService {
    ApiResponseDto getKakaoAccessToken(String code, HttpServletResponse response);

    User createKakaoUser(String token);

    User findUserByEmail(String email);
}
