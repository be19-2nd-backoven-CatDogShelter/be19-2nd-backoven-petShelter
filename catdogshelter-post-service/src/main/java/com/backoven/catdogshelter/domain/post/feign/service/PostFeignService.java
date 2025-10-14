package com.backoven.catdogshelter.domain.post.feign.service;

import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;

import java.util.List;

public interface PostFeignService {
    List<PostInventoryDTO> getPostByUserId(String userId);
}
