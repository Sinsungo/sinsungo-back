package com.example.sinsungo.user.profile;


import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.UserRepository;
import com.example.sinsungo.user.profile.dto.UserProfileRequestDto;
import com.example.sinsungo.user.profile.dto.UserProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService{

  private final UserRepository userRepository;

  @Override
  public UserProfileResponseDto getMyProfile(User user) {
    return new UserProfileResponseDto(user);
  }

  @Override
  @Transactional
  public ApiResponseDto updateMyProfile(User user, UserProfileRequestDto requestDto) {
    User DBuser = findUser(user.getId());
    DBuser.setNickname(requestDto);

    return new ApiResponseDto("프로필 수정 완료", 200);
  }

  public User findUser(Long userId){
    return userRepository.findById(userId).orElseThrow(()->
        new IllegalArgumentException("회원을 찾을 수 없습니다."));
  }

}
