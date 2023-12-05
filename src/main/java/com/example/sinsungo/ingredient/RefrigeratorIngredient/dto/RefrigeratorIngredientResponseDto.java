package com.example.sinsungo.ingredient.RefrigeratorIngredient.dto;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import lombok.Getter;

@Getter
public class RefrigeratorIngredientResponseDto {

  private String name;
  private Long quantity;
  private IngredientUnitEnum unit;
  private String remainingDays;

  public RefrigeratorIngredientResponseDto(RefrigeratorIngredient refrigeratorIngredient) {
    this.name = refrigeratorIngredient.getName();
    this.quantity = refrigeratorIngredient.getQuantity();
    this.unit = refrigeratorIngredient.getUnit();

    // refrigeratorIngredient.getDeadline()가 LocalDate이므로 바로 사용 가능
    LocalDate deadlineDate = refrigeratorIngredient.getDeadline();

    // LocalDate.now()를 사용하여 날짜 차이 계산
    Period period = Period.between(LocalDate.now(), deadlineDate);

    // Period를 일 단위로 변환하여 문자열로 설정
    this.remainingDays = String.format("%dD", period.getDays());
  }
}
