package com.backoven.catdogshelter.domain.shelter_head.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class Shelter_headWebSecurity {
//
//    private final Shelter_headJwtAuthenticationProvider jwtAuthenticationProvider;
//    private final Shelter_headJwtUtil jwtUtil;
//    private final Environment env;
//
//    @Autowired
//    public Shelter_headWebSecurity(Shelter_headJwtAuthenticationProvider jwtAuthenticationProvider,
//                                   Environment env,
//                                   Shelter_headJwtUtil jwtUtil) {
//        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
//        this.env = env;
//        this.jwtUtil = jwtUtil;
//    }
//
//    /* 우리가 만든 프로바이더를 AuthenticationManager에 등록 */
//    private AuthenticationManager shelterHeadAuthenticationManager() {
//        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
//    }
//
//    @Bean
//    protected SecurityFilterChain shelterHeadConfigure(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/shelter_head/**") // ✅ shelter_head 전용 체인
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers(HttpMethod.POST, "/shelter_head/users").permitAll()   // 보호소장 회원가입
//                        .requestMatchers(HttpMethod.POST, "/shelter_head/login").permitAll()   // 보호소장 로그인
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // 로그인 필터 등록
//        Shelter_headAuthenticationFilter authenticationFilter =
//                new Shelter_headAuthenticationFilter(shelterHeadAuthenticationManager(), env);
//        authenticationFilter.setFilterProcessesUrl("/shelter_head/login"); // 기본 /login → /shelter_head/login
//
//        http.addFilter(authenticationFilter);
//
//        // 로그인 이후 토큰을 들고 온다면 JwtFilter를 추가해서 검증
//        http.addFilterBefore(new Shelter_headJwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    /* 필터 등록용 메서드 */
//    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
//        return new Shelter_headAuthenticationFilter(authenticationManager, env);
//    }
}
