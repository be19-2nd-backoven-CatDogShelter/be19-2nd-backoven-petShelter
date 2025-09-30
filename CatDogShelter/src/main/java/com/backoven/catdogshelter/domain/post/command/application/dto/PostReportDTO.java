package com.backoven.catdogshelter.domain.post.command.application.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostReportDTO {

    @Schema(description = "자유게시글 신고에 대한 키 값 ",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 신고에 대한 기타 내용 ",example = "댓글이 재미가 없습니다.")
    private String etcDetail;

    @Schema(description = "자유게시글 신고내용 카테고리 ",example = "SPAM, ETC, MISINFO, PORN, ABUSE")
    private ReportCategory category;

    @Schema(description = "자유게시글 신고 날짜 ",example = "서버 시간 기준으로 자동으로 입력됨")
    private String createdAt;

    @Schema(description = "신고디어진 자유게시글 번호 ",example = "postId : 1")
    private int postId;

    @Schema(description = "신고디어진 자유게시글 번호 ",example = "userId : 1")
    private Integer userId;

    @Schema(description = "신고디어진 자유게시글 번호 ",example = "headId : 1")
    private Integer headId;
}
