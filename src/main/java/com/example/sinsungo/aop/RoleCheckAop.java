package com.example.sinsungo.aop;

import com.example.sinsungo.ingredient.CartIngredient.CartIngredient;
import com.example.sinsungo.ingredient.CartIngredient.CartIngredientService;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredient;
import com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredientService;
import com.example.sinsungo.refrigerator.Refrigerator;
import com.example.sinsungo.refrigerator.RefrigeratorService;
import java.util.concurrent.RejectedExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j(topic = "RoleCheckAop")
@Component
@Aspect
public class RoleCheckAop {
  @Autowired
  private RefrigeratorService refrigeratorService;

  @Autowired
  private RefrigeratorIngredientService refrigeratorIngredientService;

  @Autowired
  private CartIngredientService cartIngredientService;

  @Pointcut("execution(* com.example.sinsungo.refrigerator.RefrigeratorController.updateRefrigerator(..))")
  private void updateRefrigerator() {
  }
  @Pointcut("execution(* com.example.sinsungo.refrigerator.RefrigeratorController.deleteRefrigerator(..))")
  private void deleteRefrigerator() {
  }
  @Pointcut("execution(* com.example.sinsungo.ingredient.CartIngredient.CartIngredientController.deleteCartIngredient(..))")
  private void deleteCartIngredient() {
  }
  @Pointcut("execution(* com.example.sinsungo.ingredient.CartIngredient.CartIngredientController.updateCartIngredient(..))")
  private void updateCartIngredient() {
  }
  @Pointcut("execution(* com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredientController.deleteRefrigeratorIngredient(..))")
  private void deleteRefrigeratorIngredient() {
  }
  @Pointcut("execution(* com.example.sinsungo.ingredient.RefrigeratorIngredient.RefrigeratorIngredientController.updateRefrigeratorIngredient(..))")
  private void updateRefrigeratorIngredient() {
  }

  @Around("updateRefrigerator() || deleteRefrigerator()")
  public Object executePostRefrigeratorRoleCheck(ProceedingJoinPoint joinPoint) throws Throwable {
    // 1, 2번째 매개변수로 id, user값 가져오기
    Long RefrigeratorId = (Long) joinPoint.getArgs()[1];
    UserDetails user = (UserDetails) joinPoint.getArgs()[0];

    // 타겟 메서드에서 post 객체 가져오기
    Refrigerator refrigerator = refrigeratorService.findRefrigerator(RefrigeratorId);

    if (!refrigerator.getUser().getUsername().equals(user.getUsername())) {
      throw new RejectedExecutionException("냉장고 수정/삭제 권한없습니다");
    }

    // 핵심 기능 수행
    return joinPoint.proceed();
  }

  @Around("updateRefrigeratorIngredient() || deleteRefrigeratorIngredient()")
  public Object executePostRefrigeratorIngredientRoleCheck(ProceedingJoinPoint joinPoint) throws Throwable {
    // 1, 2번째 매개변수로 id, user값 가져오기
    Long RefrigeratorIngredientId = (Long) joinPoint.getArgs()[1];
    UserDetails user = (UserDetails) joinPoint.getArgs()[0];

    // 타겟 메서드에서 post 객체 가져오기
    RefrigeratorIngredient refrigeratorIngredient = refrigeratorIngredientService.findRefrigeratorIngredient(
        RefrigeratorIngredientId);

    if (!refrigeratorIngredient.getUser().getUsername().equals(user.getUsername())) {
      throw new RejectedExecutionException("냉장고 재료 수정/삭제 권한없습니다");
    }

    // 핵심 기능 수행
    return joinPoint.proceed();
  }
    @Around("updateCartIngredient() || deleteCartIngredient()")
    public Object executePostCartIngredientRoleCheck(ProceedingJoinPoint joinPoint) throws Throwable {
      // 1, 2번째 매개변수로 id, user값 가져오기
      Long CartIngredientId = (Long) joinPoint.getArgs()[1];
      UserDetails user = (UserDetails) joinPoint.getArgs()[0];

      // 타겟 메서드에서 post 객체 가져오기
      CartIngredient cartIngredient = cartIngredientService.findCartIngredient(CartIngredientId);

      if (!cartIngredient.getUser().getUsername().equals(user.getUsername())) {
        throw new RejectedExecutionException("장바구니 재료 수정/삭제 권한없습니다");
      }

    // 핵심 기능 수행
    return joinPoint.proceed();
  }
}
