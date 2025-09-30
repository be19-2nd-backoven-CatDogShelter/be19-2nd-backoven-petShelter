package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesRegistDTO {

    @Schema(description = "삽입할 파일의 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "삽입할 파일의 별명 ",example = "dog_friend.jpg")
    private String fileRename;

    @Schema(description = "삽입할 파일의 날짜 ",example = "서버 시간 기준으로 자동으로 입력됨")
    private String uploadedAt;

    @Schema(description = "삽입될 파일에 대한 자유게시글 번호 ",example = "posId: 1")
    private int postId;

    @Schema(description = "삽입될 파일에 대한 경로 ",example = "/uploads/post/2/dog_walk1.jpg")
    private String filePath;
}
