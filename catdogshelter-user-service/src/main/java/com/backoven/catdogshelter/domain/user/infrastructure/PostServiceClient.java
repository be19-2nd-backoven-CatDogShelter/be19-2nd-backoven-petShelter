package com.backoven.catdogshelter.domain.user.infrastructure;

import com.backoven.catdogshelter.domain.user.query.dto.ResponsePostDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* 설명. Post 도메인과의 통신을 위한 interface */
@FeignClient(name = "CATDOGSHELTER-POST-SERVICE"
            ,url = "localhost:8000"
            , configuration = FeignClientConfig.class)
public interface PostServiceClient {
    @GetMapping("/post-service/mypage/{userId}/posts")
    List<ResponsePostDTO> getUserPosts(@PathVariable String userId);
}

