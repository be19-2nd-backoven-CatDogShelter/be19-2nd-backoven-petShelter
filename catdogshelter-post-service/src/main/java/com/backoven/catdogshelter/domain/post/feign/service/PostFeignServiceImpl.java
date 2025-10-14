package com.backoven.catdogshelter.domain.post.feign.service;

import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.mapper.PostInventoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostFeignServiceImpl implements PostFeignService{
    @Override
    public List<PostInventoryDTO> getPostByUserId(String userId) {
        log.info("post서비스에서 출력: {}", PostInventoryMapper.
        List<Order> orderList = orderMapper.selectOrderByUserId(userId);
        List<OrderDTO> orderDTOList = orderToOrderDTO(orderList);
        return orderDTOList;
    }
}
