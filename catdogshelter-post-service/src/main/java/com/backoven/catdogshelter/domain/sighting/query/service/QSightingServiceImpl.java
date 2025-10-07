package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.mapper.SightingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QSightingServiceImpl implements QSightingService {

    private final SightingMapper sightingMapper;

    @Autowired
    public QSightingServiceImpl(SightingMapper sightingMapper) {
        this.sightingMapper = sightingMapper;
    }

    // 목록 가져오기
    @Override
    public List<SightingSummaryDTO> findSightingSummary(SightingSearchDTO search) {
//        List<SightingSummaryDTO> sightingSummaryDTO = sightingMapper.selectSightingSummary();
//        return sightingSummaryDTO;
        return sightingMapper.selectSightingSummary(search);
    }

    @Override
    @Transactional  // 상세 조회를 들어가면 조회수 +1
    public SightingDetailDTO findSightingDetails(int postId) {

        SightingDetailDTO sightingDetailDTO = sightingMapper.selectSightingDetails(postId);

        sightingMapper.incrementSightingView(postId);  // 조회수 업데이트

        return sightingDetailDTO;
    }

    // 확인할 신고 건이 있는가(게시글 전용)
    @Override
    public List<Integer> findPendingPostReportIds() {
        // 나중에 토큰이 추가되면 관리자만 가능하도록 하는 로직 추가
        List<SightingPendingPostReportDTO> postReportDTOs = sightingMapper.selectPendingPostReports();

        return postReportDTOs.stream()
                .filter(dto -> dto.getReportCount() >= 5)
                .map(SightingPendingPostReportDTO::getPostId)
                .collect(Collectors.toList());
    }

    // 확인할 신고 건이 있는가(댓글 전용)
    @Override
    public List<Integer> findPendingCommentReportIds() {
        // 나중에 토큰이 추가되면 관리자만 가능하도록 하는 로직 추가
        List<SightingPendingCommentReportDTO> commentReportDTOs = sightingMapper.selectPendingCommentReports();

        return commentReportDTOs.stream()
                .filter(dto -> dto.getReportCount() >= 5)
                .map(SightingPendingCommentReportDTO::getCommentId)
                .collect(Collectors.toList());
    }

    @Override
    public List<SightingPostReportDTO> findSightingPostReport(int postId) {
        // 나중에 토큰이 추가되면 관리자만 가능하도록 하는 로직 추가
        return sightingMapper.selectSightingPostReport(postId);
    }

    @Override
    public List<SightingPostCommentReportDTO> findSightingPostCommentReport(int commentId) {
        // 나중에 토큰이 추가되면 관리자만 가능하도록 하는 로직 추가
        return sightingMapper.selectSightingPostCommentReport(commentId);
    }
}
