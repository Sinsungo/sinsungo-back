package com.example.sinsungo.user.OAuth.google;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;

public interface OAuthGoogleService {
    void googleLogin(String code, HttpServletResponse response) throws JsonProcessingException;
}
