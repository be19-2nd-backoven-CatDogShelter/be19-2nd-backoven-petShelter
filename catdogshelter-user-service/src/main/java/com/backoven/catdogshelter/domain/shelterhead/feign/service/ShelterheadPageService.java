package com.backoven.catdogshelter.domain.shelterhead.feign.service;

import com.backoven.catdogshelter.domain.shelterhead.feign.dto.ShelterheadPageDTO;

public interface ShelterheadPageService {
    ShelterheadPageDTO getShelterheadById(Integer headId);
}
