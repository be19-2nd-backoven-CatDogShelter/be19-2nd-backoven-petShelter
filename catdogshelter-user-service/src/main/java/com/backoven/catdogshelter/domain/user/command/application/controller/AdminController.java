package com.backoven.catdogshelter.domain.user.command.application.controller;

import com.backoven.catdogshelter.domain.user.command.application.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
@RequestMapping("/user/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 신고 처리
    @PutMapping("/post-report/{category}/{postId}/{blind}")
    public ResponseEntity<?> PostReport(@PathVariable String category,
                                        @PathVariable int postId,
                                        @PathVariable boolean blind) {
        adminService.PostReport(category, postId, blind);

         return ResponseEntity.ok().build();
    }

    @PutMapping("/comment-report/{category}/{commentId}/{blind}")
    public ResponseEntity<?> CommentReport(@PathVariable String category,
                                           @PathVariable int commentId,
                                           @PathVariable boolean blind) {
        adminService.CommentReport(category, commentId, blind);

        return ResponseEntity.ok().build();
    }

    // 관리자 임명
    @PatchMapping("/ratingId/admin/{userId}")
    public ResponseEntity<?> promoteToAdmin(@PathVariable Integer userId) {
        adminService.promoteToAdmin(userId);

        return ResponseEntity.ok().build();
    }

}
