package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientDetailResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientRequestDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientResponseDto;
import com.example.sinsungo.refrigerator.Refrigerator;
import com.example.sinsungo.refrigerator.RefrigeratorService;
import com.example.sinsungo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefrigeratorIngredientServiceImpl implements RefrigeratorIngredientService{

  private final RefrigeratorService refrigeratorService;
  private final RefrigeratorIngredientRepository refrigeratorIngredientRepository;
  @Override
  public ApiResponseDto createRefrigeratorIngredient(RefrigeratorIngredientRequestDto requestDto,
      User user, Long refrigeratorId) {
    Refrigerator refrigerator = refrigeratorService.findRefrigerator(refrigeratorId);
    RefrigeratorIngredient refrigeratorIngredient = new RefrigeratorIngredient(requestDto, user, refrigerator);

    refrigeratorIngredientRepository.save(refrigeratorIngredient);

    return new ApiResponseDto("냉장고 재료 생성 완료", 201);
  }

  @Override
  @Transactional
  public ApiResponseDto updateRefrigeratorIngredient(Long ingredientId,
      RefrigeratorIngredientRequestDto requestDto, User user) {

    RefrigeratorIngredient ingredient = findRefrigeratorIngredient(ingredientId);

     ingredient.setName(requestDto.getName());
     ingredient.setRefrigerator(ingredient.getRefrigerator());
     ingredient.setUnit(requestDto.getUnit());
     ingredient.setCategory(requestDto.getCategory());

    return new ApiResponseDto("냉장고 재료 수정 완료", 200);
  }

  @Override
  @Transactional
  public ApiResponseDto deleteRefrigeratorIngredient(Long ingredientId, User user) {
    RefrigeratorIngredient refrigeratorIngredient = findRefrigeratorIngredient(ingredientId);

    refrigeratorIngredientRepository.delete(refrigeratorIngredient);
    return new ApiResponseDto("냉장고 재료 삭제 완료", 200);
  }

  @Override
  public Slice<RefrigeratorIngredientResponseDto> getAllRefrigeratorIngredient(User user,
      Pageable pageable) {
    return refrigeratorIngredientRepository.findAllByUser(user, pageable).map(RefrigeratorIngredientResponseDto::new);
  }

  @Override
  public RefrigeratorIngredientDetailResponseDto getRefrigeratorIngredient(User user,
      Long ingredientId) {
    RefrigeratorIngredient refrigeratorIngredient = findRefrigeratorIngredient(ingredientId);
    return new RefrigeratorIngredientDetailResponseDto(refrigeratorIngredient);
  }

  @Override
  public RefrigeratorIngredient findRefrigeratorIngredient(Long ingredientId) {
    return refrigeratorIngredientRepository.findById(ingredientId).orElseThrow(()-> new IllegalArgumentException("재료가 존재하지 않습니다."));
  }
}
