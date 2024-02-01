package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientRequestDto;
import com.example.sinsungo.ingredient.entity.Ingredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import com.example.sinsungo.refrigerator.Refrigerator;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import com.example.sinsungo.user.User;


@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "refrigerator_ingredients")
public class RefrigeratorIngredient extends Ingredient {

    private String category;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    private LocalDate deadline;

    public RefrigeratorIngredient(RefrigeratorIngredientRequestDto requestDto, User user, Refrigerator refrigerator) {
        this.category = requestDto.getCategory();
        super.name = requestDto.getName();
        super.quantity = requestDto.getQuantity();
        super.unit = requestDto.getUnit();
        super.user = user;
        this.refrigerator = refrigerator;
        this.deadline = requestDto.getDeadline();
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        super.name = name;
    }

    public void setQuantity(Long quantity) {
        super.quantity = quantity;
    }

    public void setUnit(IngredientUnitEnum unit) {
        super.unit = unit;
    }

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }
}
