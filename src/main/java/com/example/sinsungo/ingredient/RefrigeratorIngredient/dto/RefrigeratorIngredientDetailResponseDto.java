package com.example.sinsungo.ingredient.RefrigeratorIngredient.dto;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RefrigeratorIngredientDetailResponseDto {
  private String name;
  private String category;
  private Long quantity;
  private IngredientUnitEnum unit;
  private Duration remainingTime;
  private LocalDate deadline;
  private LocalDate currentTime = LocalDate.now();

  public RefrigeratorIngredientDetailResponseDto(RefrigeratorIngredient refrigeratorIngredient) {
    this.name = refrigeratorIngredient.getName();
    this.category = refrigeratorIngredient.getCategory();
    this.quantity = refrigeratorIngredient.getQuantity();
    this.unit = refrigeratorIngredient.getUnit();
    this.remainingTime = Duration.between(currentTime, refrigeratorIngredient.getDeadline());
    this.deadline = refrigeratorIngredient.getDeadline();
  }
}
