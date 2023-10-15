package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.ingredient.entity.Ingredient;
import com.example.sinsungo.refrigerator.Refrigerator;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "refrigerator_ingredients")
public class RefrigeratorIngredient extends Ingredient {
    private String category;

    private String deadline;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;
}
