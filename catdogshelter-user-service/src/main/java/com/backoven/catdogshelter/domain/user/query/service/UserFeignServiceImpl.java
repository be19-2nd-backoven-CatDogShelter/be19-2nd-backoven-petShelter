package com.backoven.catdogshelter.domain.user.query.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
import com.backoven.catdogshelter.domain.user.infrastructure.PostServiceClient;
import com.backoven.catdogshelter.domain.user.query.dto.ResponsePostDTO;
import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class UserFeignServiceImpl implements UserFeignService{
    UserRepository userRepository;
    PostServiceClient postServiceClient;

    @Autowired
    public UserFeignServiceImpl(UserRepository userRepository
            , PostServiceClient postServiceClient) {
        this.userRepository = userRepository;
        this.postServiceClient = postServiceClient;
    }
    /* 설명. 단순 회원정보 조회에서 =>
     *   + 회원정보 + 회원의 작성자(Post(다른 도메인)) */
    @Override
    public UserQueryDTO getPostUserById(String userId) {
        UserEntity user = userRepository.findByUserAccount(userId);
        UserQueryDTO userDTO =
// 매핑
        // 일반회원이 post에서 Feign client으로 조회해서 가져오기
        List<ResponsePostDTO> postList = postServiceClient.getUserPosts(userId);
        UserQueryDTO.setPostlist(postList);

        return userDTO;
    }
}
