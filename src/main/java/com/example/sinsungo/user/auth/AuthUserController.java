package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.auth.dto.AuthUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody AuthUserRequestDto requestDto) {
        ApiResponseDto result = authUserService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
