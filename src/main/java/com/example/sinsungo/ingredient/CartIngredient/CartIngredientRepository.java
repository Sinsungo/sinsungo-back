package com.example.sinsungo.ingredient.CartIngredient;

import com.example.sinsungo.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartIngredientRepository extends JpaRepository<CartIngredient, Long> {
  Slice<CartIngredient> findAllByUser(User user, Pageable pageable);
}
