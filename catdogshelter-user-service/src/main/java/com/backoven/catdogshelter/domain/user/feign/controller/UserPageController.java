package com.backoven.catdogshelter.domain.user.feign.controller;

import com.backoven.catdogshelter.domain.user.feign.dto.MyPageResponseDTO;
import com.backoven.catdogshelter.domain.user.feign.service.MyPageService;
import com.backoven.catdogshelter.domain.user.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserPageController {

    private final MyPageService myPageService;
    private final JwtUtil jwtUtil;

    // 마이페이지 (본인 확인 + 게시글 포함)
    @GetMapping("/mypage/{userId}")
    public ResponseEntity<MyPageResponseDTO> getMyPage(
            @PathVariable Integer userId,
            @RequestHeader("Authorization") String bearerToken) {

        // JWT 토큰에서 userId 추출
        String token = bearerToken.replace("Bearer ", "");
        Integer tokenUserId = jwtUtil.getUserId(token);

        // 요청 userId와 토큰 userId 비교
        if (!userId.equals(tokenUserId)) {
            throw new AccessDeniedException("본인의 마이페이지에만 접근 가능합니다.");
        }

        // MyPageService 호출 (유저정보 + 게시글리스트)
        MyPageResponseDTO response = myPageService.getMyPage(userId);

        // 정상 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}