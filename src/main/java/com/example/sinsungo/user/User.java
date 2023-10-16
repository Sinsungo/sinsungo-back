package com.example.sinsungo.user;

import com.example.sinsungo.entity.TimeStamped;
import com.example.sinsungo.user.auth.dto.AuthUserRequestDto;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "oauth_provider")
    private String oauthProvider;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String oauthProvider, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.oauthProvider = oauthProvider;
        this.role = role;
    }
}
