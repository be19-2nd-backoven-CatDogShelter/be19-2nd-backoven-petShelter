package com.backoven.catdogshelter.domain.adoption.userfeign.mapper;

import com.backoven.catdogshelter.domain.adoption.userfeign.dto.AdoptionUserResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface AdoptionUserMapper {
    List<AdoptionUserResponseDTO> findPostsByUserId(@Param("userId") Integer userId);
}
