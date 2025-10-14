package com.backoven.catdogshelter.domain.user.query.service;

import com.backoven.catdogshelter.domain.user.query.dto.UserQueryDTO;

public interface UserFeignService {
    UserQueryDTO getPostUserById(String userId);

}
