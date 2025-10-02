// src/main/java/com/backoven/catdogshelter/domain/notice/query/service/NoticeQueryService.java
package com.backoven.catdogshelter.domain.notice.query.service;

import com.backoven.catdogshelter.domain.notice.query.dto.NoticeDetailDTO;

import java.util.Map;

public interface NoticeQueryService {

    // 상세 조회
    NoticeDetailDTO getNoticeDetail(Integer id);

    // 목록 조회
    Map<String, Object> search(
            String keyword,
            String createdFrom,
            String createdTo,
            Integer page,        // 1부터
            Integer size,        // 페이지 크기
            String orderBy,      // createdAt | likeCount
            String orderDir      // ASC | DESC
    );
}
