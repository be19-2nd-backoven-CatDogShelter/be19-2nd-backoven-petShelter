package com.backoven.catdogshelter.domain.user.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAddressNameDTO {
    @Schema(description = "시/도 이름", example = "서울특별시")
    private String sidoName;
    @Schema(description = "시/군/구 이름", example = "동대문구")
    private String sigunguName;
}
