package com.backoven.catdogshelter.domain.post.query.controller;

import com.backoven.catdogshelter.domain.post.query.dto.*;
import com.backoven.catdogshelter.domain.post.query.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@Tag(name = "자유게시글 조회 API", description = "자유게시글 조회/정렬/신고 내역 조회 기능 제공")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "자유게시글 목록 조회", description = "전체 자유게시글 목록을 반환합니다.")
    @GetMapping("/posts")
    public List<PostInventoryDTO> selectPostInventory() {
        return postService.selectPostInventory();
    }

    @Operation(summary = "자유게시글 상세 조회", description = "자유게시글 ID로 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public PostDetailDTO selectPostDetail(@PathVariable int id) {
        return postService.selectPostDetail(id);
    }

    @Operation(summary = "자유게시글 조회순 내림차순 정렬", description = "조회수가 높은 순으로 자유게시글 목록을 반환합니다.")
    @GetMapping("/viewDesc")
    public List<PostInventoryDTO> viewDescPostInventory(){
        return postService.viewDescPostInventory();
    }

    @Operation(summary = "자유게시글 최신순 내림차순 정렬", description = "작성일 기준으로 최신 자유게시글 목록을 반환합니다.")
    @GetMapping("/createdAtDesc")
    public List<PostInventoryDTO> createdAtPostInventory(){
        return postService.createdAtPostInventory();
    }

    @Operation(summary = "자유게시글 좋아요순 내림차순 정렬", description = "좋아요 수가 많은 순으로 자유게시글 목록을 반환합니다.")
    @GetMapping("/likedDesc")
    public List<PostLikedDescDTO> likedPostInventory(){
        return postService.likedPostInventory();
    }

    @Operation(summary = "자유게시글 신고 내역 조회", description = "특정 자유게시글의 신고 내역을 조회합니다.")
    @GetMapping("/report/{postId}")
    public  List<PostReportDetailDTO>  reportPost(@PathVariable int postId) {
        return postService.selectPostReport(postId);
    }

    @Operation(summary = "자유게시글 댓글 신고 내역 조회", description = "자유게시글 특정 댓글의 신고 내역을 조회합니다.")
    @GetMapping("/commentReport/{postCommentId}")
    public List<PostCommentReportDetailDTO>  commentPostReport(@PathVariable int postCommentId) {
        return postService.selectPostCommentReport(postCommentId);
    }
}
