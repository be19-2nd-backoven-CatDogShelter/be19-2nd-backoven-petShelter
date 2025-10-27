package com.backoven.catdogshelter.domain.user.security;

import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestLoginDTO;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.LoginHistoryEntity;
import com.backoven.catdogshelter.domain.user.command.domain.repository.LoginHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Environment env;
    private final LoginHistoryRepository loginHistoryRepository;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                Environment env,
                                LoginHistoryRepository loginHistoryRepository) {
        super(authenticationManager);
        this.env = env;
        this.loginHistoryRepository = loginHistoryRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            RequestLoginDTO creds = null;

            // 1) JSON 바디가 있으면 우선 시도
            if (request.getContentLengthLong() > 0
                    || (request.getHeader("Content-Type") != null
                    && request.getHeader("Content-Type").toLowerCase().contains("application/json"))) {
                creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginDTO.class);
            }

            // 2) 바디가 비었으면 폼/쿼리 파라미터에서 폴백
            if (creds == null) {
                String userAccount = nvl(request.getParameter("userAccount"),
                        request.getParameter("email")); // 혹시 프론트가 email로 보낼 수도 있으니
                String userPassword = nvl(request.getParameter("userPassword"),
                        request.getParameter("password"));

                if (userAccount == null || userPassword == null) {
                    // 여기서 바로 예외를 던지면 401로 깔끔하게 떨어짐
                    throw new RuntimeException("Empty login payload");
                }
                creds = new RequestLoginDTO();
                creds.setUserAccount(userAccount);
                creds.setUserPassword(userPassword);
            }

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserAccount(), creds.getUserPassword(), Collections.emptyList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String nvl(String a, String b) {
        return (a != null && !a.isBlank()) ? a : b;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        // 자격 증명은 이미 지워질 수 있으니 전체 객체를 통째로 로그로 찍지 않는다.
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
        String username = user.getUsername();
        Integer userId  = user.getUserId();

        List<String> roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("로그인 성공: username={}, roles={}", username, roles);

        // JWT 생성
        long expMs = Long.parseLong(env.getProperty("token.expiration_time"));
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles);
        claims.put("userId", userId);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expMs))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // HttpOnly 쿠키로 내려주기 (localhost 개발 환경)
        ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN", token)
                .httpOnly(true)
                .secure(false)              // 배포(https)에서는 true
                .sameSite("Lax")            // localhost:5173 <-> 8000는 same-site로 동작
                .path("/")
                .maxAge(Duration.ofMillis(expMs))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // 간단한 응답 바디/상태
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"ok\":true}");

        // 로그인 이력 저장
        LoginHistoryEntity history = LoginHistoryEntity.builder()
                .ipAddress(request.getRemoteAddr())
                .loggedAt(LocalDateTime.now().toString())
                .userId(userId)
                .build();
        loginHistoryRepository.save(history);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        log.error("로그인 실패: {}", failed.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        String message = failed.getMessage() != null && failed.getMessage().startsWith("정지")
                ? failed.getMessage()
                : "아이디/비밀번호가 일치하지 않습니다.";

        new ObjectMapper().writeValue(response.getWriter(), new HashMap<>() {{
            put("status", 401);
            put("error", "Unauthorized");
            put("message", message);
            put("path", request.getRequestURI());
        }});
    }
}
