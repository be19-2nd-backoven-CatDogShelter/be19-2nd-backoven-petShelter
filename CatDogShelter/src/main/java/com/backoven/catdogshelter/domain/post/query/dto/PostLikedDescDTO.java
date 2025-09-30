package com.backoven.catdogshelter.domain.post.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikedDescDTO {

    @Schema(description = "자유게시글 좋아요 순 수으로 조회에 대한 키 값",
            example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 좋아요 제목", example = "좋아요!!")
    private String title;

    @Schema(description = "자유게시글 좋아요 생성일자", example = "2023-09-31 22:10:11")
    private String created_at;

    @Schema(description = "자유게시글 좋아요 수정일자", example = "2017-02-19 12:11:00")
    private String updated_at;

    @Schema(description = "자유게시글 좋아요 단 작성자 타입 구분", example = "일반회원 or 보호소장 회원")
    private String writeType;

    @Schema(description = "자유게시글 좋아요 단 작성자 이름", example = "김하진 or 한경숙")
    private String writeName;

    @Schema(description = "자유게시글 좋아요 수 증가", example = "김하진 or 한경숙")
    private int likeCount;

}
