package com.backoven.catdogshelter.domain.post.query.dto;

/* 자유게시글 목록에 대한 정보를 담아서 전송하는 DTO  */
/* mybatis를 이용해서 조회를 담당하고 entity 없이 DTO만으로도 service, repository 등에도 사용할 수 있어 entity를 사용 안함 */
/* 외래키 user_id와 head_id는 xml 쿼리문에서 조인한 후 결과를 writeName에 저장 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostInventoryDTO {

    @Schema(description = "자유게시글 상세 조회에 대한 키 값", example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 제목", example = "냥이 첫 캣타워 등반")
    private String title;

    @Schema(description = "자유게시글 생성일자", example = "2024-09-19 12:10:00")
    private String created_at;

    @Schema(description = "자유게시글 수정일자", example = "2021-12-19 12:11:00")
    private String updated_at;

    @Schema(description = "자유게시글 추천수", example = "32")
    private int view;

    @Schema(description = "자유게시글 댓글 작성자 타입 구분", example = "일반회원 or 보호소장 회원")
    private String writeType;

    @Schema(description = "자유게시글 댓글 작성자 이름", example = "김하진 or 한경숙")
    private String writeName;

}