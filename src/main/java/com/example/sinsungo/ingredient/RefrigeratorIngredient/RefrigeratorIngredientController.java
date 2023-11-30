package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientDetailResponseDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientRequestDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientResponseDto;
import com.example.sinsungo.user.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge-ingredient")
@Tag(name = "냉장고 재료 API")
public class RefrigeratorIngredientController {

  private final RefrigeratorIngredientService ingredientService;

  @Operation(summary = "냉장고 재료 생성")
  @PostMapping("/{refrigerator}")
  public ResponseEntity<ApiResponseDto> createRefrigeratorIngredient(@AuthenticationPrincipal
      UserDetailsImpl userDetails, @RequestBody RefrigeratorIngredientRequestDto requestDto, @PathVariable Long refrigerator) {
    ApiResponseDto result = ingredientService.createRefrigeratorIngredient(requestDto, userDetails.getUser(), refrigerator);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(summary = "냉장고 재료 수정")
  @PutMapping("/{ingredientId}")
  public ResponseEntity<ApiResponseDto> updateRefrigeratorIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long ingredientId, @RequestBody RefrigeratorIngredientRequestDto requestDto) {
    ApiResponseDto result = ingredientService.updateRefrigeratorIngredient(ingredientId, requestDto, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "냉장고 재료 삭제")
  @DeleteMapping("/{ingredientId}")
  public ResponseEntity<ApiResponseDto> deleteRefrigeratorIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long ingredientId) {
    ApiResponseDto result = ingredientService.deleteRefrigeratorIngredient(ingredientId, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }


  @Operation(summary = "냉장고 재료 단 건 조회")
  @GetMapping("/{ingredientId}")
  public ResponseEntity<RefrigeratorIngredientDetailResponseDto> getRefrigeratorIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long ingredientId) {
    RefrigeratorIngredientDetailResponseDto result = ingredientService.getRefrigeratorIngredient(userDetails.getUser(), ingredientId);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  // service코드 다시 짜야함
  @Operation(summary = "냉장고 재료 전체 조회")
  @GetMapping("/all")
  public ResponseEntity<Slice<RefrigeratorIngredientResponseDto>> getAllRefrigeratorIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
    Slice<RefrigeratorIngredientResponseDto> result = ingredientService.getAllRefrigeratorIngredient(userDetails.getUser(), pageable);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
