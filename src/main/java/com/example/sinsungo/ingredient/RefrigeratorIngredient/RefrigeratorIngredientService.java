package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientDetailResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientResponseDto;
import com.example.sinsungo.user.User;
import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface RefrigeratorIngredientService {

  ApiResponseDto createRefrigeratorIngredient(RefrigeratorIngredientRequestDto requestDto, User user, Long refrigeratorId);
  ApiResponseDto updateRefrigeratorIngredient(Long ingredientId,RefrigeratorIngredientRequestDto requestDto, User user);
  ApiResponseDto deleteRefrigeratorIngredient(Long ingredientId, User user);
  Slice<RefrigeratorIngredientResponseDto> getAllRefrigeratorIngredient(User user, Pageable pageable);
  RefrigeratorIngredientDetailResponseDto getRefrigeratorIngredient(User user, Long ingredientId);
  RefrigeratorIngredient findRefrigeratorIngredient(Long ingredientId);
}
