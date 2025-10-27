package com.backoven.catdogshelter.domain.user.query.controller;

import com.backoven.catdogshelter.domain.user.query.dto.*;
import com.backoven.catdogshelter.domain.user.query.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/user/admin")
public class UserQueryController {

    private UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    // 조회 -----------------------------------------------------------------------------------
    @GetMapping("/user")
    public ResponseEntity<List<AdminUserDTO>> selectUsers(@RequestParam String type){
        List<AdminUserDTO> users = userQueryService.selectUsers(type);

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<AdminUserDTO>> selectAdmin(){
        List<AdminUserDTO> users = userQueryService.selectAdmin();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/head")
    public ResponseEntity<List<UserQueryShelterHeadDTO>> selectHead(){
        List<UserQueryShelterHeadDTO> users = userQueryService.selectHead();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/login-history")
    public ResponseEntity<List<LoginHistoryDTO>> selectAllLoginHistory(@RequestParam(required = false) Integer userId,
                                                                       @RequestParam(required = false) Integer headId){

        if (userId != null && headId != null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (userId == null && headId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<LoginHistoryDTO> usersLoginHistory = userQueryService.selectAllLoginHistory(userId, headId);
        return ResponseEntity.ok().body(usersLoginHistory);
    }

    // 신고 -----------------------------------------------------------------------------------
    @GetMapping("/report-post-count")
    public ResponseEntity<Integer> selectReportsPostCount() {
        int result = userQueryService.selectReportsPostCount();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/report-comment-count")
    public ResponseEntity<Integer> selectReportsCommentCount() {
        int result = userQueryService.selectReportsCommentCount();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/report-post")
    public ResponseEntity<List<ReportedPostDTO>> selectReportsPost() {
        List<ReportedPostDTO> reports = userQueryService.selectReportsPost();

        return ResponseEntity.ok().body(reports);
    }

    @GetMapping("/report-comment")
    public ResponseEntity<List<ReportedPostCommentDTO>> selectReportsComment() {
        List<ReportedPostCommentDTO> reports = userQueryService.selectReportsComment();

        return ResponseEntity.ok().body(reports);
    }

    // 통계 -----------------------------------------------------------------------------------
}
