package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.ingredient.entity.Ingredient;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
