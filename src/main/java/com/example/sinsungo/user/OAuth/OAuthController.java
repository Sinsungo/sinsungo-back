package com.example.sinsungo.user.OAuth;

import com.example.sinsungo.user.OAuth.google.OAuthGoogleService;
import com.example.sinsungo.user.OAuth.kakao.OAuthKakaoService;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "oAuth API")
public class OAuthController {
    private final OAuthKakaoService oAuthKakaoService;
    private final OAuthGoogleService oAuthgoogleService;

    @Operation(summary = "Kakao 로그인")
    @GetMapping("/kakao")
    public ResponseEntity<TokenResponseDto> getKakaoAccessToken(@RequestParam String code, HttpServletResponse response) {
        TokenResponseDto result = oAuthKakaoService.getKakaoAccessToken(code, response);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Google 로그인")
    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<TokenResponseDto> getGoogleAccessToken(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        TokenResponseDto result = oAuthgoogleService.googleLogin(code, response);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}