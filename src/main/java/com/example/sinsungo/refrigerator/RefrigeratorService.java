package com.example.sinsungo.refrigerator;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorDetailResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorRequestDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorResponseDto;
import com.example.sinsungo.user.User;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface RefrigeratorService {

    ApiResponseDto createRefrigerator(RefrigeratorRequestDto requestDto, User user);

    ApiResponseDto updateRefrigerator(Long refrigeratorId, RefrigeratorRequestDto requestDto, User user);

    ApiResponseDto deleteRefrigerator(Long refrigeratorId, User user);

    Slice<RefrigeratorResponseDto> getAllRefrigerator(User user,Pageable pageable);

    RefrigeratorDetailResponseDto getRefrigerator(User user, Long refrigeratorId);

    Refrigerator findRefrigerator(Long refrigeratorId);
}
