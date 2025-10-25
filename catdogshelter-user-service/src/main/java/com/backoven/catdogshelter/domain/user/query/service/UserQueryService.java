package com.backoven.catdogshelter.domain.user.query.service;

import com.backoven.catdogshelter.domain.user.query.dto.*;
import com.backoven.catdogshelter.domain.user.query.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserQueryService {
    private final UserMapper userMapper;

    @Autowired
    public UserQueryService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserQueryDTO> selectUsers(String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type.toLowerCase());
        log.info("입력값 = {}", type);
        List<UserQueryDTO> users = userMapper.selectUsers(params);
        log.info("되어라 = {}", users);
        return users;
    }

    public List<UserQueryDTO> selectAdmin() {
        return userMapper.selectAdmin();
    }

    public List<UserQueryShelterHeadDTO> selectHead() {
        return userMapper.selectHead();
    }

    public List<LoginHistoryDTO> selectAllLoginHistory(Integer userId, Integer headId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", userId);
        params.put("headId", headId);

        List<LoginHistoryDTO> usersLoginHistory = userMapper.selectAllLoginHistory(params);
        return usersLoginHistory;
    }

    public int selectReportsPostCount() {
        return userMapper.selectReportsPostCount();
    }

    public int selectReportsCommentCount() {
        return userMapper.selectReportsCommentCount();
    }

    public List<ReportedPostDTO> selectReportsPost() {
        return userMapper.selectReportsPost();
    }

    public List<ReportedPostCommentDTO> selectReportsComment() {
        return userMapper.selectReportsComment();
    }
}
