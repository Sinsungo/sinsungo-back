package com.example.sinsungo.ingredient.CartIngredient.dto;

import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import lombok.Getter;

@Getter
public class CartIngredientRequestDto {
  private String name;
  private Long quantity;
  private IngredientUnitEnum unit;
  private String memo;

}
