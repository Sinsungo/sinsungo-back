package com.example.sinsungo.ingredient.RefrigeratorIngredient;

import com.example.sinsungo.ingredient.RefrigeratorIngredient.dto.RefrigeratorIngredientResponseDto;
import com.example.sinsungo.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorIngredientRepository  extends JpaRepository<RefrigeratorIngredient, Long> {

  Slice<RefrigeratorIngredient> findAllByUser(User user, Pageable pageable);
}
