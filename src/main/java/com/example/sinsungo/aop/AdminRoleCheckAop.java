package com.example.sinsungo.aop;

import com.example.sinsungo.user.UserDetailsImpl;
import com.example.sinsungo.user.UserRoleEnum;
import java.util.concurrent.RejectedExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j(topic = "AdminRoleCheckAop")
@Component
@Aspect
public class AdminRoleCheckAop {

  @Pointcut("execution(* com.example.sinsungo.notice.NoticeController.deleteNotice(..))")
  private void deleteNotice() {
  }
  @Pointcut("execution(* com.example.sinsungo.notice.NoticeController.updateNotice(..))")
  private void updateNotice() {
  }
  @Pointcut("execution(* com.example.sinsungo.notice.NoticeController.createNotice(..))")
  private void createNotice() {
  }
  @Pointcut("execution(* com.example.sinsungo.notice.NoticeController.getNotices(..))")
  private void getNotice() {
  }

  @Around("createNotice() || getNotice() || updateNotice() ||"
      + " deleteNotice()")
  public Object executeReportRoleCheck(ProceedingJoinPoint joinPoint) throws Throwable {

    UserDetailsImpl user = (UserDetailsImpl) joinPoint.getArgs()[0];
    if (!user.getRole().equals(UserRoleEnum.ADMIN)) {
      throw new RejectedExecutionException("권한이 없습니다");
    }

    // 핵심 기능 수행
    return joinPoint.proceed();
  }
}
