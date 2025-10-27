package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportedPostDTO {
    private Integer id;
    private String title;
    private String writer;
    private String category;
    private String status;
    private String createdAt;
    private Integer view;
    private Integer reportCount;
}

