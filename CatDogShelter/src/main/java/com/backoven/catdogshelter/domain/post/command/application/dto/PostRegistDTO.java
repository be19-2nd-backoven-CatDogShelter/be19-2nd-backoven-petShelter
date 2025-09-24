package com.backoven.catdogshelter.domain.post.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRegistDTO {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private Integer userId;
    private Integer headId;
}
