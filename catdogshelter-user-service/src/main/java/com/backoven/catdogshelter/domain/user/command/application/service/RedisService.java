package com.backoven.catdogshelter.domain.user.command.application.service;

// redis 부분에서 이메일 관련 부분을 담당하는 보조 서비스

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    // 인증코드 저장
    public void saveAuthCode(String email, String code, long minutes) {
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(minutes));
    }

    // 인증코드 조회
    public String getAuthCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    // 인증코드 삭제
    public void deleteAuthCode(String email) {
        redisTemplate.delete(email);
    }
}
