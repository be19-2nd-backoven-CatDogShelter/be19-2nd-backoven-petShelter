package com.backoven.catdogshelter.domain.user.command.application.dto.requestdto;

/* redis에서 아이디, 답변을 확인할 때 쓰이는 DTO */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestVerifyUserDTO {
    private String userAccount;
    private String answer;
}
