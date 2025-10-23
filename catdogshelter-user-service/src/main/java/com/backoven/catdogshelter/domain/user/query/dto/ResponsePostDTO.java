package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.Data;

@Data
public class ResponsePostDTO {
    private String title;
    private String created_at;
    private String updated_at;
    private int view;
    private int likeCount;
}