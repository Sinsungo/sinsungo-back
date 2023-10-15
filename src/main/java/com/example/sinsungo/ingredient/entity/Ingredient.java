package com.example.sinsungo.ingredient.entity;

import com.example.sinsungo.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.Getter;
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

    protected String unit;
}
