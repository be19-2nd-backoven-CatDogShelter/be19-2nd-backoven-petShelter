package com.backoven.catdogshelter.domain.shelterhead.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShelterheadPageDTO {
    private Integer headId;
    private String ceoName;                     // 사업자명
    private String ceoName2;                    // 사업자명2
    private String email;                       // 이메일
    private String headPhone;                   // 사업자 전화번호
    private String pwd;                         // 암호화를 하기 위해서 entity에서 선언한 부분을 Shelter_headServiceImpl에서 수정
    private String companyName;                 // 보호소 이름
    private String bizNumber;                   // 사업자 번호
    private String corNumber;                   // 법인등록번호
    private String companyAddress;              // 보호소 주소
    private String openDate;                    // 개업일
    private String closeDate;                   // 폐업일
    private String sigunguId;                   // 시군구 코드
    private String headAccount;                 // 회원가입 시 생성될 고유 아이디

    private List<VolunteerAssociationDTO> associations;
}
