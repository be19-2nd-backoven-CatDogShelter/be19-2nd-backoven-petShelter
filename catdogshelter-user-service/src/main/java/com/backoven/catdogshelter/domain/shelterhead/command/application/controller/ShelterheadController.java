package com.backoven.catdogshelter.domain.shelterhead.command.application.controller;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseFindShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.ShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.service.ShelterheadService;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestPasswordDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.responsedto.ResponseModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/shelter-head")
@Controller
public class ShelterheadController {

    private ShelterheadService shelterheadService;
    private ModelMapper modelMapper;

    public ShelterheadController(ShelterheadService shelterheadService,
                                 ModelMapper modelMapper) {
        this.shelterheadService = shelterheadService;
        this.modelMapper = modelMapper;
    }

    // 보호소장 회원가입
    @PostMapping("/regist")
    // 회원가입 할 때, 사용자의 요청은 RequestRegistUserDTO(뒷 부분)가 받고,
    // 다시 반환할 때는 ResponseRegistUserDTO(앞 부분)으로 반환한다.
    public ResponseEntity<ResponseRegistShelterheadDTO> registUser(@RequestBody RequestRegistShelterheadDTO newUser){
        // RequestRegistUserDTO로 만은 값을 UserDTO 형태로 변환시킴
        ShelterheadDTO userDTO = modelMapper.map(newUser, ShelterheadDTO.class);

        shelterheadService.registUser(userDTO);

        ResponseRegistShelterheadDTO responseUser = modelMapper.map(userDTO, ResponseRegistShelterheadDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    // 마이페이지
    @GetMapping("/mypage/{shelterId}")
    public ResponseEntity<ResponseFindShelterheadDTO> getUsers(@PathVariable String shelterId){
        ShelterheadDTO shelterHeadDTO = shelterheadService.getShelter_headById(shelterId);

        ResponseFindShelterheadDTO responseFindShelterHeadDTO =
                modelMapper.map(shelterHeadDTO, ResponseFindShelterheadDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseFindShelterHeadDTO);
    }

}


