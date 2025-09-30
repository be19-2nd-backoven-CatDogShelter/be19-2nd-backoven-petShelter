package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCommentModifyDTO {
    @Schema(description = "자유게시글 댓글 수정 키 값",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "수정하려는 댓글 내용",example = "이건 좀 아닌디")
    private String content;

    @Schema(description = "수정일",example = "서버 시간을 기준으로 측정되기 때문에 예제가 필요 없음")
    private String updatedAt;

}
