package com.backoven.catdogshelter.domain.sighting.query.mapper;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SightingMapper {

    List<SightingSummaryDTO> selectSightingSummary(@Param("search") SightingSearchDTO search,
                                                   @Param("offset") int offset,
                                                   @Param("limit") int limit);

    int countSightingSummary(@Param("search") SightingSearchDTO search);

    SightingDetailDTO selectSightingDetails(Map<String, Object> postId);    // 상세 보기

    void incrementSightingView(int postId);

    List<SightingPostReportDTO> selectSightingPostReport(int postId);

    List<SightingPostCommentReportDTO> selectSightingPostCommentReport(int commentId);
}
