package com.example.sinsungo.ingredient.CartIngredient.dto;

import com.example.sinsungo.ingredient.CartIngredient.CartIngredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import lombok.Getter;

@Getter
public class CartIngredientResponseDto {
  private String name;
  private Long quantity;
  private IngredientUnitEnum unit;
  private String memo;

  public CartIngredientResponseDto(CartIngredient cartIngredient) {
    this.name = cartIngredient.getName();
    this.quantity = cartIngredient.getQuantity();
    this.unit = cartIngredient.getUnit();
    this.memo = cartIngredient.getMemo();
  }
}
