package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentRegistDTO {

    @Schema(description = "자유게시글 댓글 키 값",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 댓글 내용",example = "스팸 문자입니다.")
    private String content;

    @Schema(description = "자유게시글 댓글 수정 생성일, 현재 서버 시간 기준으로 측정"
            ,example = "서버 시간을 기준으로 측정되기 때문에 예제가 필요 없음 ")
    private String createdAt;

    @Schema(description = "댓글에 해당되는 자유게사글",example = " postId : 1")
    private String postId;

    @Schema(description = "댓글을 단 일반회원",example = " userId : 1")
    private Integer userId;

    @Schema(description = "댓글을 단 보호소장 회원",example = " headId : 1")
    private Integer headId;
}
