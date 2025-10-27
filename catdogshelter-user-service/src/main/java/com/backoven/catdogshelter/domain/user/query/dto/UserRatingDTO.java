package com.backoven.catdogshelter.domain.user.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRatingDTO {
    @Schema(description = "등급 id")
    private int id;
    @Schema(description = "등급 이름")
    private String name;
}
