package com.backoven.catdogshelter.domain.user.command.application.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestResetUserPasswordDTO {
    private String userAccount;   // 사용자 아이디
    private String verificationCode; // 이메일로 받은 인증 코드
    private String newPassword;     // 입력할 새로운 비밀번호
}
