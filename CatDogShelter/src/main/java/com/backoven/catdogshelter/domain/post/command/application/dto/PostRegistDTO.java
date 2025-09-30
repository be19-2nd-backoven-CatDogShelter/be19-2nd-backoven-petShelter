package com.backoven.catdogshelter.domain.post.command.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRegistDTO {

    @Schema(description = "자유게시글 삽입에 대한 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 삽입 제목 ",example = "진돗개를 입얍했어요")
    private String title;

    @Schema(description = "자유게시글 삽입 내용 ",example = "요양원에서 진돗개를 입양했어요. 집에 하루종일 계실 아버지를 위해서")
    private String content;

    @Schema(description = "자유게시글 삽입 날짜 ",example = "서버 시간 기준으로 자동으로 입력됨")
    private String createdAt;

    @Schema(description = "자유게시글 작성 일반 회원 ",example = "userId: 1")
    private Integer userId;

    @Schema(description = "자유게시글 작성 보호소장 회원 ",example = "headId: 1")
    private Integer headId;

}
