package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostModifyDTO {

    @Schema(description = "자유게시글 수정에 대한 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 수정 제목 ",example = "관찰일지 15주차")
    private String title;

    @Schema(description = "자유게시글 수정 내용 ",example = "이제 걷고 짖기도 하네요. 무셔")
    private String content;

    @Schema(description = "자유게시글 수정 날짜 ",example = "서버 시간 기준으로 자동으로 입력됨")
    private String updatedAt;





}
