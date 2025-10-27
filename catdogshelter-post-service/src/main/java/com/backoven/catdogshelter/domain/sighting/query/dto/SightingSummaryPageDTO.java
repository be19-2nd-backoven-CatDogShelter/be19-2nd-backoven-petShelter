package com.backoven.catdogshelter.domain.sighting.query.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SightingSummaryPageDTO {
    private List<SightingSummaryDTO> content;    // 현재 페이지 데이터
    private int totalCount;     // 전체 개수
    private int totalPages;     // 전체 페이지 수
    private int currentPage;    // 현재 페이지 번호
    private int pageSize;       // 페이지당 사이즈
}
