package com.example.sinsungo.user.auth;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.UserRepository;
import com.example.sinsungo.user.UserRoleEnum;
import com.example.sinsungo.user.auth.dto.AuthUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${database.username}")
    private String ADMIN_TOKEN;
    @Override
    public ApiResponseDto signup(AuthUserRequestDto requestDto) {
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

        User user = new User(requestDto.getUsername(), passwordEncoder.encode(requestDto.getPassword()), null, role);

        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", 201);
    }

    @Override
    public boolean checkAdmin(String adminToken) {
        if (adminToken.equals(ADMIN_TOKEN)) {
            return true;
        }
        return false;
    }
}
