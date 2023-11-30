package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientRequestDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientResponseDto;
import com.example.sinsungo.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CartIngredientService {
  ApiResponseDto createCartIngredient(CartIngredientRequestDto requestDto, User user);
  ApiResponseDto updateCartIngredient(Long cartId, CartIngredientRequestDto requestDto, User user);
  ApiResponseDto deleteCartIngredient(Long cartId, User user);
  Slice<CartIngredientResponseDto> getAllCartIngredient(User user, Pageable pageable);
  CartIngredientResponseDto getCartIngredient(Long cartId, User user);
  CartIngredient findCartIngredient(Long cartId);
}
