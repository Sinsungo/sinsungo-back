package com.example.sinsungo.user.profile;


import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.user.UserDetailsImpl;
import com.example.sinsungo.user.profile.dto.UserProfileRequestDto;
import com.example.sinsungo.user.profile.dto.UserProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserProfileController {

  private final UserProfileService userProfileService;

  @GetMapping
  public UserProfileResponseDto getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return userProfileService.getMyProfile(userDetails.getUser());
  }

  @PutMapping
  public ResponseEntity<ApiResponseDto> updateMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserProfileRequestDto requestDto) {
    ApiResponseDto result = userProfileService.updateMyProfile(userDetails.getUser(), requestDto);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
