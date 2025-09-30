package com.backoven.catdogshelter.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesModifyDTO {

    @Schema(description = "수정할 파일의 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "수정할 파일의 별명 ",example = "dog_friend.jpg")
    private String fileRename;

    @Schema(description = "수정할 파일의 날짜 ",example = "서버 시간 기준으로 자동으로 입력됨")
    private String uploadedAt;

    @Schema(description = "수정할 파일의 경로 ",example = "/uploads/post/1/dog_walk1.jpg")
    private String filePath;
}
