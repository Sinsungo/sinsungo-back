package com.example.sinsungo.user;

import com.example.sinsungo.entity.TimeStamped;
import com.example.sinsungo.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "users")
public class User extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String oauthProvider;

    private UserRoleEnum user_role;

    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.oauthProvider = requestDto.getOauthProvider();
        this.user_role = requestDto.getUser_role();
    }
}
