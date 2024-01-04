package com.example.sinsungo.refrigerator.dto;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientResponseDto;
import com.example.sinsungo.refrigerator.Refrigerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class RefrigeratorDetailResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<RefrigeratorIngredientResponseDto> ingredientList;

    public RefrigeratorDetailResponseDto(Refrigerator refrigerator) {
        this.id = refrigerator.getId();
        this.title = refrigerator.getTitle();
        this.ingredientList = refrigerator.getRefrigeratorIngredientList().stream()
            .map(RefrigeratorIngredientResponseDto::new)
            .collect(Collectors.toList());
        this.createdAt = refrigerator.getCreatedAt();
        this.modifiedAt = refrigerator.getModifiedAt();
    }
}
