package com.backoven.catdogshelter.domain.shelterhead.feign.controller;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseFindShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.feign.dto.ShelterheadPageDTO;
import com.backoven.catdogshelter.domain.shelterhead.feign.service.ShelterheadPageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/shelter-head"))
public class ShelterHeadPageController {

    private final ShelterheadPageService shelterheadPageService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShelterHeadPageController(ShelterheadPageService shelterheadPageService,
                                 ModelMapper modelMapper) {
        this.shelterheadPageService = shelterheadPageService;
        this.modelMapper = modelMapper;
    }

    // 마이페이지
    @GetMapping("/mypage/{shelterHeadId}")
    public ResponseEntity<ResponseFindShelterheadDTO> getShelteHead(@PathVariable Integer shelterHeadId){
        ShelterheadPageDTO shelterHeadPageDTO = shelterheadPageService.getShelterheadById(shelterHeadId);

        ResponseFindShelterheadDTO response =
                modelMapper.map(shelterHeadPageDTO, ResponseFindShelterheadDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
