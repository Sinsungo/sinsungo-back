package com.example.sinsungo.refrigerator.dto;

import com.example.sinsungo.refrigerator.Refrigerator;
import lombok.Getter;

@Getter
public class RefrigeratorDetailResponseDto {
    private String title;
    private String description;

    public RefrigeratorDetailResponseDto(Refrigerator refrigerator) {
        this.title = refrigerator.getTitle();
        this.description = refrigerator.getDescription();
    }
}
