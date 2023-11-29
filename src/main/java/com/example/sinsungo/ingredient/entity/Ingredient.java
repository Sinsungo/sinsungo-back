package com.example.sinsungo.ingredient.entity;

import com.example.sinsungo.common.entity.TimeStamped;
import com.example.sinsungo.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.Getter;
import com.example.sinsungo.user.User;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Ingredient extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String name;

    protected Long quantity;

    @Enumerated(value = EnumType.STRING)
    protected IngredientUnitEnum unit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

//    @ManyToOne
//    @JoinColumn(name = "refrigerator_id")
//    protected Refrigerator refrigerator;


}
