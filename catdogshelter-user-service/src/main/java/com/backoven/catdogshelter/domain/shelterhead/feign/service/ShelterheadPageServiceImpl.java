package com.backoven.catdogshelter.domain.shelterhead.feign.service;

import com.backoven.catdogshelter.domain.shelterhead.feign.dto.ShelterheadPageDTO;
import com.backoven.catdogshelter.domain.shelterhead.feign.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.domain.aggregate.entity.ShelterheadEntity;
import com.backoven.catdogshelter.domain.shelterhead.command.domain.repository.ShelterheadRepository;
import com.backoven.catdogshelter.domain.user.feign.infrastructure.PostServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterheadPageServiceImpl implements ShelterheadPageService {
    private final ShelterheadRepository shelterheadRepository;
    private final ModelMapper modelMapper;
    private final PostServiceClient postServiceClient;

    @Autowired
    public ShelterheadPageServiceImpl(ShelterheadRepository shelterheadRepository,
                                      PostServiceClient postServiceClient,
                                      ModelMapper modelMapper) {
        this.shelterheadRepository = shelterheadRepository;
        this.modelMapper = modelMapper;
        this.postServiceClient = postServiceClient;
    }

    public ShelterheadPageDTO getShelterheadById(Integer headId) {

        // jpa로 shelterhead 정보 조회해서 가져오기
        ShelterheadEntity shelterHead = shelterheadRepository.findById(headId).get();

        // 엔티티를 dto로 매핑
        ShelterheadPageDTO shelterHeadPageDTO = modelMapper.map(shelterHead, ShelterheadPageDTO.class);

        // 봉사 모집 공고 리스트 가져오기
        List<VolunteerAssociationDTO> associations = postServiceClient.getHeadsAssociations(headId);

        shelterHeadPageDTO.setAssociations(associations);

        return  shelterHeadPageDTO;
    }
}
