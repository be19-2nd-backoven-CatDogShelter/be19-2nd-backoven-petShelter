package com.backoven.catdogshelter.domain.user.query.mapper;

import com.backoven.catdogshelter.domain.user.query.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    // 전체 일반 회원 조회
    List<AdminUserDTO> selectUsers(Map<String, Object> params);

    // 관리자 조회
    List<AdminUserDTO> selectAdmin();

    // 보호소 회원 조회
    List<UserQueryShelterHeadDTO> selectHead();

    // 로그인 이력 조회
    List<LoginHistoryDTO> selectAllLoginHistory(Map<String, Integer> params);

    // 신고 건수가 5회가 넘는 게시글이 있는가
    Integer selectReportsPostCount();

    // 신고 건수가 5회가 넘는 댓글이 있는가
    Integer selectReportsCommentCount();

    // 신고 건수가 5회 넘는 게시글 확인
    List<ReportedPostDTO> selectReportsPost();

    // 신고 건수가 5회 넘는 댓글 확인
    List<ReportedPostCommentDTO> selectReportsComment();
}
