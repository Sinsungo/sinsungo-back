package com.example.sinsungo.refrigerator.dto;

import com.example.sinsungo.refrigerator.Refrigerator;
import lombok.Getter;

@Getter
public class RefrigeratorResponseDto {

    private String title;

    public RefrigeratorResponseDto(Refrigerator refrigerator) {
        this.title = refrigerator.getTitle();
    }
}
