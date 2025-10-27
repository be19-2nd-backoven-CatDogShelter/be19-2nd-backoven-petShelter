package com.backoven.catdogshelter.domain.user.query.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserQueryShelterHeadDTO {
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
    private Integer postCount;
    private Integer commentCount;
    private Integer volunteerCount;

    private UserAddressNameDTO headAddress;
    private LoginHistoryDTO headLoginHistory;
}
