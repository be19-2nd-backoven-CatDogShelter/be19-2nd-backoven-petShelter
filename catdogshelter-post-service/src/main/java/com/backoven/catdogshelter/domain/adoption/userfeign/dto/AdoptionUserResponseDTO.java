package com.backoven.catdogshelter.domain.adoption.userfeign.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdoptionUserResponseDTO {
    private Long id;
    private String title;
    private String createdAt;
    private String updatedAt;
    private int viewCount;
    private int likeCount;
    private int commentCount;
    private String boardType = "입양게시판";
}
