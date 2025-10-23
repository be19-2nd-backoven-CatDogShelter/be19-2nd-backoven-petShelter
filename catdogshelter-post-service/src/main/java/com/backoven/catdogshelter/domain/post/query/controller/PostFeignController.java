package com.backoven.catdogshelter.domain.post.query.controller;

import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.feign.dto.ResponsePostDTO;
import com.backoven.catdogshelter.domain.post.feign.service.PostFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostFeignController {
    private final PostFeignService postFeignService;

    @Autowired
    public PostFeignController(PostFeignService postFeignService) {
        this.postFeignService = postFeignService;
    }

    @GetMapping("/mypage/{userId}/posts")
    public ResponseEntity<List<ResponsePostDTO>> getUserOrders(@PathVariable int userId){
        List<PostInventoryDTO> orderDTOList = postFeignService.getPostByUserId(userId);
        List<ResponseOrderDTO> returnValue = orderDTOToResponseOrder(orderDTOList);
        return ResponseEntity.ok().body(returnValue);
    }
}
