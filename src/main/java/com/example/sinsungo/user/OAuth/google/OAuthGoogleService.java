package com.example.sinsungo.user.OAuth.google;

import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;

public interface OAuthGoogleService {
    TokenResponseDto googleLogin(String code, HttpServletResponse response) throws JsonProcessingException;
}
