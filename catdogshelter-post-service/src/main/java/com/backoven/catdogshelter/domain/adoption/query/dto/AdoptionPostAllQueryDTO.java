package com.backoven.catdogshelter.domain.adoption.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdoptionPostAllQueryDTO {
    private int id;

    private String title;

    private String animalType;   // CAT, DOG

    private String displayDate;  // created_at 또는 updated_at

    private String writerName;   // user_name 또는 company_name

    private String breed;

    private String userRating;   // 회원 등급

    private int view;

    private int recommendCount;  // 추천 수

    private String sidoName;     // 시/도 이름

    private String sigunguName;  // 시/군/구 이름

    // 썸네일 파일명 추가
    private String storageFileName;
}
