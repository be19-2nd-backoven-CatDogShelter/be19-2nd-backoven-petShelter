package com.backoven.catdogshelter.domain.adoption.userfeign.service;

import com.backoven.catdogshelter.domain.adoption.userfeign.mapper.AdoptionUserMapper;
import com.backoven.catdogshelter.domain.adoption.userfeign.dto.AdoptionUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionUserService {

    private final AdoptionUserMapper adoptionUserMapper;

    public List<AdoptionUserResponseDTO> getPostsByUserId(Integer userId) {
        return adoptionUserMapper.findPostsByUserId(userId);
    }
}