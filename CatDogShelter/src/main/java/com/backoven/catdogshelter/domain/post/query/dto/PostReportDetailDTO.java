package com.backoven.catdogshelter.domain.post.query.dto;

/* 신고 내역을 조회하기 위해 필요한 DTO */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostReportDetailDTO {

    @Schema(description = "자유게시글 신고 횟수", example = "4")
    private int reportCount;

    @Schema(description = "자유게시글 신고 상세 키 값", example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 신고 카테고리", example = "SPAM, ETC, MISINFO, PORN, ABUSE")
    private String category;

    @Schema(description = "자유게시글 신고 기타 내용", example = "드라마 스포를 해요")
    private String etc_detail;

    @Schema(description = "자유게시글 신고 생성일자", example = "2025-03-02 12:00:00")
    private String created_at;

    @Schema(description = "신고되어진 자유게시글", example = "post_id : 1")
    private int post_id;

    @Schema(description = "자유게시글 신ㄱ자 타입 구분", example = "일반회원 or 보호소장 회원")
    private String writeType;

    @Schema(description = "자유게시글 좋아요 단 작성자 이름", example = "김하진 or 한경숙")
    private String writeName;
}
