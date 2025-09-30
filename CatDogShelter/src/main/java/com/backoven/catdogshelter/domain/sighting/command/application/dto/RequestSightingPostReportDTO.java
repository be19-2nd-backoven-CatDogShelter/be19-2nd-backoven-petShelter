package com.backoven.catdogshelter.domain.sighting.command.application.dto;

import com.backoven.catdogshelter.common.util.ReportCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestSightingPostReportDTO {
    @Schema(description = "신고 카테고리", example = "2")
    private ReportCategory reportCategory;
    @Schema(description = "기타 카테고리인 경우 기타 내용", example = "~~일을 했습니다.")
    private String etcDetail;
    @Schema(description = "신고한 게시글의 댓글 번호", example = "2")
    private int postId;
    @Schema(description = "신고한 유저의 유저 번호", example = "2")
    private Integer headId;
    @Schema(description = "산고한 보호소의 보호소 번호", example = "2")
    private Integer userId;

}
