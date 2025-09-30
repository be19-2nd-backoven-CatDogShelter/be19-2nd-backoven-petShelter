package com.backoven.catdogshelter.domain.post.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostFilesDTO {

    @Schema(description = "자유게시글 파일에 대한 키 값", example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 파일 별명", example = "강아지 사진.jpg")
    private String file_rename;

    @Schema(description = "자유게시글 파일 수정일자", example = "2023-10-27 13:42:21")
    private String uploaded_at;
}
