package com.backoven.catdogshelter.domain.adoption.query.mapper;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AdoptionQueryMapper {

    long countAll();

    // 전체 게시판 목록 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPostsPaging(
            @Param("size") int size,
            @Param("offset") int offset,
            @Param("sortType") String sortType
    );

    // 게시글 조회
    AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId);
    // 조회수 증가 쿼리 (+게시글 조회마다 증가)
    int updateAdoptionPostView(int adoptionPostId);
    // 조회수순 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPostsByView();
    // 추천수순 조회
    List<AdoptionPostAllQueryDTO> selectAllAdoptionPostByLiked();
    // 키워드 조회
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria condition);
    // 컨디션 조회

    // 조건 검색 결과 목록 조회 (List)
    List<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(
            @Param("criteria") SearchCriteria criteria,
            @Param("size") int size,
            @Param("offset") int offset
    );

    // 조건 검색 결과 총 개수 조회
    long countAllByCondition(@Param("criteria") SearchCriteria criteria);

}
