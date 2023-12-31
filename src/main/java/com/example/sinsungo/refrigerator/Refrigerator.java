package com.example.sinsungo.refrigerator;

import com.example.sinsungo.common.entity.TimeStamped;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.refrigerator.dto.RefrigeratorRequestDto;
import com.example.sinsungo.user.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "refrigerators")
public class Refrigerator extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "refrigerator", cascade = CascadeType.REMOVE)
    private List<RefrigeratorIngredient> refrigeratorIngredientList = new ArrayList<>();

    public Refrigerator(RefrigeratorRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
