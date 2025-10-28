package com.backoven.catdogshelter.domain.sighting.query.service;

import com.backoven.catdogshelter.domain.sighting.query.dto.*;
import com.backoven.catdogshelter.domain.sighting.query.mapper.SightingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QSightingServiceImpl implements QSightingService {

    private final SightingMapper sightingMapper;

    @Autowired
    public QSightingServiceImpl(SightingMapper sightingMapper) {
        this.sightingMapper = sightingMapper;
    }

    // 목록 가져오기
    @Override
    public SightingSummaryPageDTO findSightingSummary(SightingSearchDTO search, int page, int size) {
//        List<SightingSummaryDTO> sightingSummaryDTO = sightingMapper.selectSightingSummary();
//        return sightingSummaryDTO;
        int offset = (page -1 ) * size;
        int totalCount = sightingMapper.countSightingSummary(search);
        List<SightingSummaryDTO> content = sightingMapper.selectSightingSummary(search, offset, size);
        int totalPage = (int) Math.ceil((double) totalCount / size);


        return new SightingSummaryPageDTO(content, totalCount, totalPage, page, size);
    }

    @Override
    @Transactional  // 상세 조회를 들어가면 조회수 +1
    public SightingDetailDTO findSightingDetails(int postId, Boolean userType, Integer userId) {

        Map<String, Object> params =  new HashMap<>();
        params.put("postId", postId);
        if(userType == null) {}
        else if(userType) {
            params.put("userId", userId);
        } else {
            params.put("headId", userId);
        }

        SightingDetailDTO sightingDetailDTO = sightingMapper.selectSightingDetails(params);

        sightingMapper.incrementSightingView(postId);  // 조회수 업데이트

        return sightingDetailDTO;
    }

    @Override
    public List<SightingPostReportDTO> findSightingPostReport(int postId) {
        return sightingMapper.selectSightingPostReport(postId);
    }

    @Override
    public List<SightingPostCommentReportDTO> findSightingPostCommentReport(int commentId) {
        return sightingMapper.selectSightingPostCommentReport(commentId);
    }
}
