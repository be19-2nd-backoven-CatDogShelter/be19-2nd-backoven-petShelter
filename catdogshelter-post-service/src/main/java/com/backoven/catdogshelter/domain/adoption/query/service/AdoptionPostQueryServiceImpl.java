package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import com.backoven.catdogshelter.domain.adoption.query.mapper.AdoptionQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import static com.backoven.catdogshelter.domain.adoption.query.template.Template.getSqlSession;


@Slf4j
@Service
public class AdoptionPostQueryServiceImpl implements AdoptionPostQueryService {
    private AdoptionQueryMapper adoptionQueryMapper;

    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPosts(int page, int size, String sortType) {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper = sqlSession.getMapper(AdoptionQueryMapper.class);

        int offset = page * size;
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionQueryMapper.selectAllAdoptionPostsPaging(size, offset, sortType);

        sqlSession.close();
        return adoptionPostList;
    }

    public long countAllPosts() {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper = sqlSession.getMapper(AdoptionQueryMapper.class);
        long count = adoptionQueryMapper.countAll();
        sqlSession.close();
        return count;
    }


    @Override
    public AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId) {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper = sqlSession.getMapper(AdoptionQueryMapper.class);
        adoptionQueryMapper.updateAdoptionPostView(adoptionPostId); // 조회수 증가
        sqlSession.commit(); // 조회수 update commit
        AdoptionPostDetailQueryDTO adoptionPostDetailDTO = adoptionQueryMapper.selectAdoptionPostById(adoptionPostId);
        sqlSession.close();
        return adoptionPostDetailDTO;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByView() {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper =sqlSession.getMapper(AdoptionQueryMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionQueryMapper.selectAllAdoptionPostsByView();
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByLiked() {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper =sqlSession.getMapper(AdoptionQueryMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionQueryMapper.selectAllAdoptionPostByLiked();
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria keyword) {
        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper =sqlSession.getMapper(AdoptionQueryMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionQueryMapper.selectAdoptionPostByKeyword(keyword);
        sqlSession.close();
        return adotpionPostList;
    }

    @Override
    public Page<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(
            SearchCriteria criteria, int page, int size) {

        SqlSession sqlSession = getSqlSession();
        adoptionQueryMapper = sqlSession.getMapper(AdoptionQueryMapper.class);

        int offset = page * size;

        long total = adoptionQueryMapper.countAllByCondition(criteria); // 총 개수 조회

        List<AdoptionPostAllQueryDTO> list =
                adoptionQueryMapper.selectAdoptionPostByAnimalCondition(criteria, size, offset);

        sqlSession.close();

        return new PageImpl<>(list, PageRequest.of(page, size), total);
    }

}
