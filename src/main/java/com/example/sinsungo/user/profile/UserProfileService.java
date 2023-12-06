package com.example.sinsungo.user.profile;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.profile.dto.UserProfileRequestDto;
import com.example.sinsungo.user.profile.dto.UserProfileResponseDto;

public interface UserProfileService {

  UserProfileResponseDto getMyProfile(User user);

  ApiResponseDto updateMyProfile(User user, UserProfileRequestDto requestDto);
}
