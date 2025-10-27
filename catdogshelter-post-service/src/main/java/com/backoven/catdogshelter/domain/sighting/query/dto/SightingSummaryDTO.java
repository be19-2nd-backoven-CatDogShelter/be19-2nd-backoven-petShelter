package com.backoven.catdogshelter.domain.sighting.query.dto;

/* 설명. 게시글 목록 DTO */

import com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.enumeration.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSummaryDTO {
    @Schema(description = "게시글 ID", example = "5")
    private int id;
    @Schema(description = "게시글 제목", example = "고양이를 목격했어요")
    private String title;
    @Schema(description = "작성 시간", example = "2025.10.01 10:08:10")
    private String createdAt;
    @Schema(description = "수정 시간", example = "2025.10.01 12:08:10")
    private String updatedAt;

    private AnimalType animalType;  // 동물 타입
    private String breed;           // 품종
    private String color;           // 색상
    private String sightedAt;       // 목격 시간
    private String sightedPlace;

    private SightingAddressNameDTO address;
    private SightingFileDTO file;


    @Schema(description = "조회수", example = "5")
    private int view;
    @Schema(description = "추천수", example = "5")
    private int likeCount;
    @Schema(description = "작성자")
    private SightingUserDTO writer;
}
