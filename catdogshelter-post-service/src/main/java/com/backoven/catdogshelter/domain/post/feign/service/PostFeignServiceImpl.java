package com.backoven.catdogshelter.domain.post.feign.service;

import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.mapper.PostInventoryMapper;
import com.backoven.catdogshelter.domain.post.query.mapper.PostQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostFeignServiceImpl implements PostFeignService{
    private final PostInventoryMapper postInventoryMapper;

    public PostFeignServiceImpl(PostInventoryMapper postInventoryMapper) {
        this.postInventoryMapper = postInventoryMapper;
    }

    @Override
    public List<PostInventoryDTO> getPostByUserId(String userId) {
        log.info("post서비스에서 출력: {}", PostInventoryMapper.
        List<PostEntity> postList = postInventoryMapper.selectPostInventory();
        List<OrderDTO> orderDTOList = orderToOrderDTO(orderList);
        return orderDTOList;
    }
}
