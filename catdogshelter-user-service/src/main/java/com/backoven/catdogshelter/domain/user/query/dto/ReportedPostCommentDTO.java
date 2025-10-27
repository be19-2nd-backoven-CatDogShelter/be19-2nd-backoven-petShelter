package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportedPostCommentDTO {
    private Integer id;
    private String content;
    private String writer;
    private String category;
    private String status;      // "처리 필요"
    private String createdAt;
    private Integer postId;
    private Integer reportCount;
}
