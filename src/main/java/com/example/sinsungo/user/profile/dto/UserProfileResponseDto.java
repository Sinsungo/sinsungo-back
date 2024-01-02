package com.example.sinsungo.user.profile.dto;

import com.example.sinsungo.user.User;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

  private String username;
  private String nickname;

  public UserProfileResponseDto(User user) {
    username = user.getUsername();
    nickname = user.getNickname();
  }
}
