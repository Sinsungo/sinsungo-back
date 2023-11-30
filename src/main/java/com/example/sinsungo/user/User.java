package com.example.sinsungo.user;

import com.example.sinsungo.common.entity.TimeStamped;
import com.example.sinsungo.user.OAuth.OAuthRoleEnum;
import com.example.sinsungo.user.profile.dto.UserProfileRequestDto;
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
    private String nickname;

    @Column
    private String password;

    @Column(name = "oauth_provider")
    private OAuthRoleEnum oauthProvider;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public void setNickname(UserProfileRequestDto requestDto){
        this.nickname = requestDto.getNickname();
    }

    public User(String username, String password, String nickname, OAuthRoleEnum oauthProvider, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.oauthProvider = oauthProvider;
        this.role = role;
    }
}
