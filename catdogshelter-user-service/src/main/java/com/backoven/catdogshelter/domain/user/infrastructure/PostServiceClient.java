package com.backoven.catdogshelter.domain.user.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CATDOGSHELTER-POST-SERVICE"
            ,url = "localhost:8000"
            , configuration = FeignClientConfig.class)
public interface PostServiceClient {
    @GetMapping("/post-service/mypage/{userId}/posts")
    List<ResponsePostDTO> getUserPosts(@PathVariable String userId);
}
