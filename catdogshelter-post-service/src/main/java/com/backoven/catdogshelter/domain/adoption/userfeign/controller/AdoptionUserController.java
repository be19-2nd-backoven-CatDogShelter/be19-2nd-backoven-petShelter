package com.backoven.catdogshelter.domain.adoption.userfeign.controller;

import com.backoven.catdogshelter.domain.adoption.userfeign.dto.AdoptionUserResponseDTO;
import com.backoven.catdogshelter.domain.adoption.userfeign.service.AdoptionUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adoption-post")
@RequiredArgsConstructor
public class AdoptionUserController {

    private final AdoptionUserService adoptionUserService;

    // 일반회원의 입양글 목록 조회
    @GetMapping("/user/{userId}/adoption")
    public ResponseEntity<List<AdoptionUserResponseDTO>> getPostSummaryByUserId(
            @PathVariable Integer userId) {

        List<AdoptionUserResponseDTO> list = adoptionUserService.getPostsByUserId(userId);
        return ResponseEntity.ok(list);
    }
}