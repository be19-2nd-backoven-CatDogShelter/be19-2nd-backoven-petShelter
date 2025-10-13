package com.backoven.catdogshelter.domain.notice.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCreateDTO {
    private String title;
    private String content;
}
