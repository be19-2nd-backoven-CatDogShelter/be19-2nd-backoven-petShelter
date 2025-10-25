package com.backoven.catdogshelter.domain.shelterhead.command.application.dto.responsedto;

import com.backoven.catdogshelter.domain.shelterhead.feign.dto.VolunteerAssociationDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseFindShelterheadDTO {
    private Integer headId;
    private String ceoName;
    private String ceoName2;
    private String headAccount;
    private String email;
    private String headPhone;
    private String companyName;
    private String bizNumber;
    private String corNumber;
    private String companyAddress;
    private String openDate;
    private String closeDate;
    private String sigunguId;
    private String address;

    private List<VolunteerAssociationDTO> associations;
}
