package com.backoven.catdogshelter.domain.post.query.dto;

/* PostDetailDTO와 PostInventoryDTO와 겹쳐지는 내용(번호, 제목, 작성일, 작성자, 조회 수, 추천수)이 많으므로 상속 관계 사용 */
/* PostInventory 타입으로 선언 시 같은 값을 중복해서 사용하므로 유지보수성이 떨어짐 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDetailDTO extends PostInventoryDTO {

    @Schema(description = "자유게시글 별 상세 목목 제목", example = "강아지 미용하고 왔어요")
    private String ratingName;

    @Schema(description = "자유게시글 별 상세 목목 내용", example = "털이 길어서 미용을 맡겼더니 완전 " +
            "다른 강아지가 되어 돌아왔네요. 너무 깔끔하고 귀여워요.")
    private String content;

    @Schema(description = "자유게시글 별 첨부된 파일", example = "files : 1")
    private List<PostFilesDTO> files;

    @Schema(description = "자유게시글 별 첨부된 댓글", example = "comments : 1")
    private List<PostCommentDTO> comments;
}
