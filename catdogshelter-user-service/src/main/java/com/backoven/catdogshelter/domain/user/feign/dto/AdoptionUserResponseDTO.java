package com.backoven.catdogshelter.domain.user.feign.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdoptionUserResponseDTO {
    private Integer id;             // 게시글 ID
    private String title;        // 제목
    private String createdAt;    // 작성일
    private String updatedAt;    // 수정일(없으면 createdAt)
    private int viewCount;       // 조회수
    private int likeCount;       // 좋아요 수
    private int commentCount;    // 댓글 수
    private String boardType = "입양게시판"; // 게시판 이름
}