package com.backoven.catdogshelter.domain.user.command.application.dto.requestlogin;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String userAccount;
    private String userPassword;
}
