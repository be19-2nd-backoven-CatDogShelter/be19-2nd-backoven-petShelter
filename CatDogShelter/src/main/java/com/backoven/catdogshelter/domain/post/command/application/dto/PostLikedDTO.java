package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedDTO {

    @Schema(description = "자유게시글 좋아요 수에 대한 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "좋아요가 가리키는 자유게시글 ",example = "postId : 1")
    private int postId;

    @Schema(description = "자유게시글 좋아요를 한 일반 회원 ",example = "userId : 1")
    private Integer userId;

    @Schema(description = "자유게시글 좋아요를 한 보호소장 회원 ",example = "headId : 1")
    private Integer headId;
}
