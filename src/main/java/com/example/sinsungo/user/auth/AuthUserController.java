package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.UserDetailsImpl;
import com.example.sinsungo.user.auth.dto.SignInRequestDto;
import com.example.sinsungo.user.auth.dto.SignUpRequestDto;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthUserController {
    private final AuthUserService authUserService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        log.info("in");
        ApiResponseDto result = authUserService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //로그인
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody SignInRequestDto requestDto, HttpServletResponse response) {
        TokenResponseDto result = authUserService.signIn(requestDto, response);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @Operation(summary = "로그아웃")
    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponseDto> logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){

        ApiResponseDto result = authUserService.logout(userDetails.getUser(),request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @Operation(summary = "엑세스 토큰 재발급")
    @GetMapping("/reissue")
    public TokenResponseDto reissue(
        @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request) {
        TokenResponseDto result = authUserService.reissue(userDetails.getUser(), request);
        return result;
    }
}
