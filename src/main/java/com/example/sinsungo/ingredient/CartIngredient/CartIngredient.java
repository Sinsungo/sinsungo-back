package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.ingredient.CartIngredient.dto.CartIngredientRequestDto;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientRequestDto;
import com.example.sinsungo.ingredient.entity.Ingredient;
import com.example.sinsungo.ingredient.entity.IngredientUnitEnum;
import com.example.sinsungo.refrigerator.Refrigerator;
import com.example.sinsungo.user.User;
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
@Table(name = "cart_ingredients")
public class CartIngredient extends Ingredient {

    private String memo;

    public CartIngredient(CartIngredientRequestDto requestDto, User user) {
        this.memo = requestDto.getMemo();
        super.name = requestDto.getName();
        super.quantity = requestDto.getQuantity();
        super.unit = requestDto.getUnit();
        super.user = user;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
}
