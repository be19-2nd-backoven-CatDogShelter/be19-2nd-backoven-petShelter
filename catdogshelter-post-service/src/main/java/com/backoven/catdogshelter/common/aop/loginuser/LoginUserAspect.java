package com.backoven.catdogshelter.common.aop.loginuser;

import com.backoven.catdogshelter.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor    // final 필드나 @NonNull이 붙은 필드를 자동으로 생성자 주입
public class LoginUserAspect {
    private final JwtTokenProvider jwtTokenProvider;

}
