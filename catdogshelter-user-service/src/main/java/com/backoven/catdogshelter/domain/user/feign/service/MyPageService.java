package com.backoven.catdogshelter.domain.user.feign.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
import com.backoven.catdogshelter.domain.user.feign.dto.AdoptionUserResponseDTO;
import com.backoven.catdogshelter.domain.user.feign.dto.MyPageResponseDTO;
import com.backoven.catdogshelter.domain.user.feign.infrastructure.PostServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final PostServiceClient postServiceClient;

    public MyPageResponseDTO getMyPage(Integer userId) {
        // DB에서 유저정보 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        // Feign으로 post-service에서 작성한 글 목록 조회
        List<AdoptionUserResponseDTO> myPosts = postServiceClient.getAdoptionUser(userId);
        // 유저정보 + 게시글리스트 통합 DTO로 반환
        return new MyPageResponseDTO(user, myPosts);
    }
}