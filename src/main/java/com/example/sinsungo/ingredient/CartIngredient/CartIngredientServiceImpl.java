package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientRequestDto;
import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientResponseDto;
import com.example.sinsungo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartIngredientServiceImpl implements CartIngredientService{
  private final CartIngredientRepository cartIngredientRepository;

  @Override
  public ApiResponseDto createCartIngredient(CartIngredientRequestDto requestDto, User user) {
    CartIngredient cartIngredient = new CartIngredient(requestDto, user);
    cartIngredientRepository.save(cartIngredient);
    return new ApiResponseDto("장바구지 재료 생성 완료", 201);
  }

  @Override
  @Transactional
  public ApiResponseDto updateCartIngredient(Long cartId, CartIngredientRequestDto requestDto,
      User user) {
    CartIngredient cartIngredient = findCartIngredient(cartId);

    cartIngredient.setMemo(requestDto.getMemo());
    cartIngredient.setQuantity(requestDto.getQuantity());
    cartIngredient.setName(requestDto.getName());
    cartIngredient.setUnit(requestDto.getUnit());

    return new ApiResponseDto("장바구니 재료 수정 완료", 200);
  }

  @Override
  @Transactional
  public ApiResponseDto deleteCartIngredient(Long cartId, User user) {
    CartIngredient cartIngredient = findCartIngredient(cartId);
    cartIngredientRepository.delete(cartIngredient);
    return new ApiResponseDto("장바구니 재료 삭제 완료", 200);
  }

  @Override
  public Slice<CartIngredientResponseDto> getAllCartIngredient(User user, Pageable pageable) {
    return cartIngredientRepository.findAllByUser(user, pageable).map(CartIngredientResponseDto::new);
  }

  @Override
  public CartIngredientResponseDto getCartIngredient(Long cartId, User user) {
    CartIngredient cartIngredient = findCartIngredient(cartId);
    return new CartIngredientResponseDto(cartIngredient);
  }

  @Override
  public CartIngredient findCartIngredient(Long cartId) {
    return cartIngredientRepository.findById(cartId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 장바구니 재료입니다."));
  }
}
