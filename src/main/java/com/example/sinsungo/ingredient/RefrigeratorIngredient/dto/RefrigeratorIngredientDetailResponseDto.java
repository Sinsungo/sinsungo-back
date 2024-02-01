package com.example.sinsungo.ingredient.RefrigeratorIngredient.dto;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import java.time.LocalDate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RefrigeratorIngredientDetailResponseDto {
  private Long id;
  private String name;
  private String category;
  private Long quantity;
  private IngredientUnitEnum unit;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private LocalDate deadline;

  public RefrigeratorIngredientDetailResponseDto(RefrigeratorIngredient refrigeratorIngredient) {
    this.id = refrigeratorIngredient.getId();
    this.name = refrigeratorIngredient.getName();
    this.category = refrigeratorIngredient.getCategory();
    this.quantity = refrigeratorIngredient.getQuantity();
    this.unit = refrigeratorIngredient.getUnit();
    this.createdAt = refrigeratorIngredient.getCreatedAt();
    this.modifiedAt = refrigeratorIngredient.getModifiedAt();
    this.deadline = refrigeratorIngredient.getDeadline();

  }
}
