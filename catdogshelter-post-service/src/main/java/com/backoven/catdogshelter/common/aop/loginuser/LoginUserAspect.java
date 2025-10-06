package com.backoven.catdogshelter.common.aop.loginuser;

import com.backoven.catdogshelter.common.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor    // final 필드나 @NonNull이 붙은 필드를 자동으로 생성자 주입
public class LoginUserAspect {
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletRequest request;

    @Before("@annotation(com.backoven.catdogshelter.common.aop.loginuser.LoginUser)")
    public void before(JoinPoint joinPoint) {
        String token = jwtTokenProvider.resolveToken(request);

        if(token != null && jwtTokenProvider.validateToken(token)){
            Integer userId = jwtTokenProvider.getUserId(token);
            UserContextHolder.setUserId(userId);
        } else {
            throw new IllegalStateException("유효하지 않은 토큰입니다.");
        }
    }

    @After("@annotation(com.backoven.catdogshelter.common.aop.loginuser.LoginUser)")
    public void after(JoinPoint joinPoint) {
        UserContextHolder.clear();
    }
}
