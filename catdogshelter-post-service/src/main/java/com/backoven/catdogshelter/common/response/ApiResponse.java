package com.backoven.catdogshelter.common.response;

/* 설명. 응답 포멧(상태 코드, 메시지, 데이터 ...) */

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}