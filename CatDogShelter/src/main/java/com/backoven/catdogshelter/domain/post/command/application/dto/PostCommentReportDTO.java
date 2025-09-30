package com.backoven.catdogshelter.domain.post.command.application.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  PostCommentReportDTO {

    @Schema(description = "자유게시글 댓글 신고 키 값",example = "auto_increment이므로 예제가 필요 없음")
    private int id;

    @Schema(description = "자유게시글 댓글 신고 기타 내용",example = "드라마 마지막을 스포했어요")
    private String etcDetail;

    @Schema(description = "자유게시글 댓글 신고 카테고리",example = "SPAM, ETC, MISINFO, PORN, ABUSE")
    private ReportCategory category;

    @Schema(description = "자유게시글 댓글 신고 생성일",example = "서버 시간 기준으로 생성되 예제가 필요 없음")
    private String createdAt;

    @Schema(description = "신고되어진 댓글 번호 ",example = "commentId : 1")
    private int commentId;

    @Schema(description = "신고한 일반 회원 번호 ",example = "userId : 1")
    private Integer userId;

    @Schema(description = "신고한 보호소장 회원 번호 ",example = "headId : 1")
    private Integer headId;
}
