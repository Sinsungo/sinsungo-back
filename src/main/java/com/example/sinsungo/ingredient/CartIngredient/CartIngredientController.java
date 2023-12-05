package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientRequestDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientResponseDto;
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
@RequestMapping("/api/cart-ingredient")
@Tag(name = "장바구니 재료 API")
public class CartIngredientController {

  private final CartIngredientService cartIngredientService;

  @Operation(summary = "장바구니 재료 생성")
  @PostMapping
  public ResponseEntity<ApiResponseDto> createCartIngredient(@RequestBody CartIngredientRequestDto requestDto, @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    ApiResponseDto result = cartIngredientService.createCartIngredient(requestDto, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(summary = "장바구니 재료 수정")
  @PutMapping("/{cartId}")
  public ResponseEntity<ApiResponseDto> updateCartIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long cartId, @RequestBody CartIngredientRequestDto requestDto) {
    ApiResponseDto result = cartIngredientService.updateCartIngredient(cartId, requestDto, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "장바구니 재료 삭제")
  @DeleteMapping("/{cartId}")
  public ResponseEntity<ApiResponseDto> deleteCartIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long cartId) {
    ApiResponseDto result = cartIngredientService.deleteCartIngredient(cartId, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }


  @Operation(summary = "장바구니 재료 단 건 조회")
  @GetMapping("/{cartId}")
  public ResponseEntity<CartIngredientResponseDto> getCartIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long cartId) {
    CartIngredientResponseDto result = cartIngredientService.getCartIngredient(cartId, userDetails.getUser());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "장바구니 재료 전체 조회")
  @GetMapping("/all")
  public ResponseEntity<Slice<CartIngredientResponseDto>> getAllCartIngredient(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
    Slice<CartIngredientResponseDto> result = cartIngredientService.getAllCartIngredient(userDetails.getUser(), pageable);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
