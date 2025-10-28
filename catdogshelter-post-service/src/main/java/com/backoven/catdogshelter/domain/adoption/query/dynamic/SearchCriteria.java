package com.backoven.catdogshelter.domain.adoption.query.dynamic;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {

    // 키워드 검색 (title/content/breed 중 하나)
    private String searchType;   // "title", "content", "breed"
    private String keyword;      // 검색어

    // 동물 조건 검색 (여러 개 조건 동시 적용 가능)
    private String animalType;   // "강아지"/"고양이"
    private String gender;       // "MALE"/"FEMALE"
    private String vaccination;  // "Y"/"N"
    private String neutering;    // "Y"/"N"

    // 지역 조건 추가 (프론트에서 보내는 값 매핑)
    private String sidoName;
    private String sigunguName;

    // 페이징 + 정렬 옵션도 여기에서 한 번에 받도록 확장
    private Integer page;
    private Integer size;
    private String sortType;  // view / liked
}
