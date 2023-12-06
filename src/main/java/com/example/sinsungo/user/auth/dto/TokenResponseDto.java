package com.example.sinsungo.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {
  private final String atk;
  private final String rtk;
}
