package com.backoven.catdogshelter.domain.shelterhead.command.application.controller;

import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestFindShelterheadIdDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestResetShelterheadPasswordDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.requestdto.RequestVerifyShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseFindShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto.ResponseRegistShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.ShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.application.service.ShelterheadService;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/shelter-head")
@Controller
public class ShelterheadController {

    private final ShelterheadService shelterheadService;
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

    // redis를 이용한 이메일로 인증 코드 보내는 부분
    @PostMapping("/password/verify")
    public ResponseEntity<String> sendVerificationCode(@RequestBody RequestVerifyShelterheadDTO dto) {
        shelterheadService.sendVerificationCode(dto.getHeadAccount(), dto.getEmail());
        return ResponseEntity.ok("인증코드가 이메일로 발송되었습니다.");
    }

    @PostMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody RequestResetShelterheadPasswordDTO dto) {
        try {
            shelterheadService.resetUserPassword(dto.getHeadAccount(), dto.getVerificationCode(), dto.getNewPassword());
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/find-id")
    public ResponseEntity<String> findUserId(@RequestBody RequestFindShelterheadIdDTO dto){
        shelterheadService.findShelterheadIdByEmail(dto.getEmail());
        return ResponseEntity.ok("등록된 이메일로 아이디가 전송되었습니다.");
    }

}


