package com.backoven.catdogshelter.domain.user.feign.dto;

import com.backoven.catdogshelter.common.entity.UserEntity;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyPageResponseDTO {

    private Integer userId;          // 유저 ID
    private String userName;      // 이름
    private String email;         // 이메일
    private String phone;         // 전화번호
    private String address;       // 주소
    private String joinDate;      // 가입일
    private String ratingName;    // 등급명 (선택)
    private List<AdoptionUserResponseDTO> myPosts; // 내가 작성한 게시글 리스트

    public MyPageResponseDTO(UserEntity user, List<AdoptionUserResponseDTO> myPosts) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phone = user.getUserPhone();
        this.address = user.getDetailAddress();
        this.joinDate = user.getActivationDate();
        this.ratingName = user.getRating() != null ? user.getRating().getName() : "일반회원";
        this.myPosts = myPosts;
    }
}