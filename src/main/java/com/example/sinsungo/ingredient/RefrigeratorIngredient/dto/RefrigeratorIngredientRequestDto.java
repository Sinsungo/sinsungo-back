package com.example.sinsungo.ingredient.RefrigeratorIngredient.dto;

import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RefrigeratorIngredientRequestDto {

  private String name;
  private String category;
  private Long quantity;
  private IngredientUnitEnum unit;
  private LocalDateTime deadline;

}
