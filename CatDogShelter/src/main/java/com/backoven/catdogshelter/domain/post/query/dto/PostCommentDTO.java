package com.backoven.catdogshelter.domain.post.query.dto;

/* 조회만 할때는 id(key)가 필요없다고 생각한다. 하지만 추후 수정, 삭제 등이 일어날 경우를 대비해 key 값은 계속해서 추가하는 것이 좋다.  */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentDTO {

    @Schema(description = "자유게시글 댓글에 대한 키 값", example = "auto_increment이므로 예제가 필요 없음")
    private int id;                 // 자유게시글 댓글 번호

    @Schema(description = "자유게시글 댓글 내용", example = "너무 귀여워요")
    private String content;         // 자유게시글 댓글 내용

    @Schema(description = "자유게시글 댓글 생성일자", example = "2025-09-01 11:00:00")
    private String created_at;      // 자유게시글 생성 일자

    @Schema(description = "자유게시글 댓글 수정일자", example = "2025-09-27 13:42:21")
    private String updated_at;      // 자유게시글 수정 일자

    @Schema(description = "자유게시글 댓글 작성자 타입 구분", example = "일반회원 or 보호소장 회원")
    private String writeType;       // 회원 타입 구분

    @Schema(description = "자유게시글 댓글 작성자 이름", example = "김하진 or 한경숙")
    private String writeName;       // 자유게시글 댓글 작성자
}
