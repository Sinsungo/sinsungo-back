package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.common.RedisUtil;
import com.example.sinsungo.jwt.JwtUtil;
import com.example.sinsungo.user.OAuth.OAuthRoleEnum;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.UserRepository;
import com.example.sinsungo.user.UserRoleEnum;
import com.example.sinsungo.user.auth.dto.SignInRequestDto;
import com.example.sinsungo.user.auth.dto.SignUpRequestDto;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    @Value("${database.username}")
    private String ADMIN_TOKEN;
    @Override
    @Transactional
    public ApiResponseDto signup(SignUpRequestDto requestDto) {
        Optional<User> checkUsername = userRepository.findByUsername(requestDto.getUsername());
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }

        if (requestDto.getAdminToken() != null) {
            if (checkAdmin(requestDto.getAdminToken())) {
                requestDto.setAdmin(true);
            }
        }

        UserRoleEnum role = UserRoleEnum.MEMBER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 관리자 계정을 생성할 수 없습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(requestDto.getUsername(), passwordEncoder.encode(requestDto.getPassword()), OAuthRoleEnum.ORIGIN, role);

        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", 201);
    }

    @Override
    public TokenResponseDto signIn(SignInRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        User user = findUserByUserName(username);

        String password = requestDto.getPassword();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }

        // Access Token 생성 및 헤더에 추가
        String accessToken = jwtUtil.createToken(user.getUsername(), user.getRole());
        String rtk = jwtUtil.createRefreshToken(username, user.getRole());
        // RefreshToken Redis 저장
        redisUtil.saveRefreshToken(user.getUsername(), rtk);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

        return new TokenResponseDto(accessToken, rtk);
    }
    @Override
    @Transactional
    public ApiResponseDto logout(User user, HttpServletRequest request) {
        String atk = jwtUtil.resolveToken(request);
        String username = user.getUsername();

        if(!redisUtil.hasKey(username)) { // key가 username인 refreshToken의 존재유무 검사
            throw  new IllegalArgumentException("username을 key값으로 가지는 refreshToken이 존재하지 않습니다.");
        }

        // Redis에서 RefreshToken 삭제
        redisUtil.delete(username);

        // AccessToken 유효시간 가지고 오기 및 BlackList에 저장
        Long expiration = jwtUtil.expireTime(atk);
        redisUtil.setBlackList(atk, expiration);

        return new ApiResponseDto("로그아웃 완료", 200);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
    }

    @Override
    public TokenResponseDto reissue(User user, HttpServletRequest request) {
        String newArk = jwtUtil.createToken(user.getUsername(), user.getRole());
        return new TokenResponseDto(newArk, null);
    }

    @Override
    public boolean checkAdmin(String adminToken) {
        if (adminToken.equals(ADMIN_TOKEN)) {
            return true;
        }
        return false;
    }
}
