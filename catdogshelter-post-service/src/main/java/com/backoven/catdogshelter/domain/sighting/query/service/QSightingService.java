package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;

import java.util.List;

public interface QSightingService {
    SightingSummaryPageDTO findSightingSummary(SightingSearchDTO search, int page, int size);

    SightingDetailDTO findSightingDetails(int postId, Boolean userType, Integer userId);

    List<SightingPostReportDTO> findSightingPostReport(int postId);

    List<SightingPostCommentReportDTO> findSightingPostCommentReport(int commentId);
}
