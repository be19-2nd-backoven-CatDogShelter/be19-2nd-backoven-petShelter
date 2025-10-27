package com.backoven.catdogshelter.domain.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/user/regist",
            "/user/login"
    );

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String path = request.getRequestURI();

        // 1) CORS preflight / 공개 경로는 무조건 통과
        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::endsWith);
        if ("OPTIONS".equalsIgnoreCase(request.getMethod()) || isPublic) {
            chain.doFilter(request, response);
            return;
        }

        // 2) 토큰: 쿠키 우선, 없으면 헤더
        String token = extractFromCookie(request);
        String headerToken = extractFromHeader(request);

        // 3) 검증: 쿠키가 있으면 먼저, 없거나 무효면 헤더로 보조 시도
        Authentication auth = null;
        if (token != null && jwtUtil.validateToken(token)) {
            auth = jwtUtil.getAuthentication(token);
        } else if (headerToken != null && jwtUtil.validateToken(headerToken)) {
            auth = jwtUtil.getAuthentication(headerToken);
        }

        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    private String extractFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private String extractFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("ACCESS_TOKEN".equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
