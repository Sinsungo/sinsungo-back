package com.example.sinsungo.ingredient.RefrigeratorIngredient.dto;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RefrigeratorIngredientResponseDto {

  private String name;
  private Long quantity;
  private IngredientUnitEnum unit;
  private Duration remainingTime;
  private LocalDateTime currentTime = LocalDateTime.now();


  public RefrigeratorIngredientResponseDto(RefrigeratorIngredient refrigeratorIngredient) {
    this.name = refrigeratorIngredient.getName();
    this.quantity = refrigeratorIngredient.getQuantity();
    this.unit = refrigeratorIngredient.getUnit();
    this.remainingTime = Duration.between(currentTime, refrigeratorIngredient.getDeadline());
  }
}
