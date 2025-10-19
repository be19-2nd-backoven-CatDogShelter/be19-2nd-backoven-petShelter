package com.backoven.catdogshelter.domain.shelterhead.command.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ShelterheadRedisService {
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
