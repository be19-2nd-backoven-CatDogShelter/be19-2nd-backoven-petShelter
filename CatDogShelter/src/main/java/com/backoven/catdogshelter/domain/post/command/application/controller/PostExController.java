package com.backoven.catdogshelter.domain.post.command.application.controller;

import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.application.service.PostService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/post")
@Tag(name = "게시글 API", description = "자유게시글 등록/수정/삭제/댓글/파일/좋아요/신고 관련 API")
public class PostExController {

    private final PostService postService;

    public PostExController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "자유게시글 등록", description = " 새로운 자유게시글을 등록합니다.")
    @PostMapping("/regist")
    public void registPost(@RequestBody PostRegistDTO postregist){
        postService.registPost(postregist);
    }

    @Operation(summary = "자유게시글 수정", description = " 새로운 자유게시글로 수정합니다.")
    @PutMapping("/modify")
    public void modifyPost(@RequestBody PostModifyDTO postmodify){
        postService.modifyPost(postmodify);
    }

    /* 프론트에서 삭제할 자유게시글 번호만 받아서 사용하기 때문에 DTO을 따로 사용하지 않고 Map을 사용해서
       String 타입으로 받은 후에 다시 int 타입으로 전환 */
    @Operation(summary = "자유게시글 삭제", description = " 자유게시글을 삭제합니다.")
    @DeleteMapping("/delete")
    public void deletePost(@RequestBody Map<String, String> postDelete){
        int id = Integer.parseInt(postDelete.get("id"));
        postService.deletePost(id);
    }

    @Operation(summary = "자유게시글 댓글 등록", description = " 자유게시글에 대한 댓글을 등록합니다. " +
            "단 파일이 삭제되어 있는 상태면 불가능")
    @PostMapping("/comment/regist")
    public void registPostComment(@RequestBody PostCommentRegistDTO postCommentRegist){
        postService.registPostComment(postCommentRegist);
    }

    @Operation(summary = "자유게시글 댓글 수정", description = " 자유게시글게시글에 대한 댓글을 수정합니다." +
            " 단 파일이 삭제되어 있는 상태면 불가능")
    @PutMapping("/comment/modify")
    public void modifyPostComment(@RequestBody PostCommentModifyDTO postCommentModify){
        postService.modifyPostComment(postCommentModify);
    }

    @Operation(summary = "자유게시글 댓글 삭제", description = " 자유게시글에 대한 댓글 삭제합니다. " +
            "단 파일이 삭제되어 있는 상태면 불가능")
    @DeleteMapping("/comment/delete")
    public void deletePostComment(@RequestBody Map<String, String> postCommentDelete){
        int id = Integer.parseInt(postCommentDelete.get("id"));
        postService.deletePostComment(id);
    }

    @Operation(summary = "자유게시글 파일 등록", description = " 자유게시글별 파일들을 등록합니다. ")
    @PostMapping("/files/regist")
    public void registPostFiles(@RequestBody PostFilesRegistDTO postFilesRegist){
        postService.registPostFiles(postFilesRegist);
    }

    @Operation(summary = "자유게시글 파일 수정", description = " 자유게시글별 파일들을 수정합니다. ")
    @PutMapping("/files/modify")
    public void modifyPostFiles(@RequestBody PostFilesModifyDTO postFilestModify){
        postService.modifyPostFiles(postFilestModify);
    }

    @Operation(summary = "자유게시글 파일 삭제", description = " 자유게시글별 파일들을 삭제합니다. ")
    @DeleteMapping("/files/delete")
    public void deletePostFiles(@RequestBody PostFilesDeleteDTO postFilesDelete){
        postService.deletePostFiles(postFilesDelete);
    }


    @Operation(summary = "자유게시글 파일 좋아요", description = "자유게시글에 대해서 회원들이 좋아요를 누를 수 있습니다. " +
            "단 한 자유게시글당 회원 한명 하나만 좋아요가 가능합니다. ")
    @PostMapping("/liked")
    public void likedPost(@RequestBody PostLikedDTO postLiked){
        postService.likedPost(postLiked);
    }

    @Operation(summary = "자유게시글 신고", description = "자유게시글에 대해서 회원들이 신고를 누를 수 있습니다. " +
            "단 한 자유게시글당 회원 한명 하나만 신고가 가능합니다. ")
    @PostMapping("/report")
    public void reportPost(@RequestBody PostReportDTO postReport){
        postService.reportPost(postReport);
    }

    @Operation(summary = "자유게시글 댓글 신고", description = "자유게시글 댓글에 대해서 회원들이 신고를 누를 수 있습니다. " +
            "단 한 자유게시글 댓글당 회원 한명 하나만 신고가 가능합니다. ")
    @PostMapping("/comment/report")
    public void reportPostComment(@RequestBody PostCommentReportDTO postCommentReport){
        postService.reportPostComment(postCommentReport);
    }

}

