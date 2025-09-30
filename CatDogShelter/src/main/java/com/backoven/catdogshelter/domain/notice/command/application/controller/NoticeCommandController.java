// NoticeCommandController.java
package com.backoven.catdogshelter.domain.notice.command.application.controller;

import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeDTO;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeUpdateDTO;
import com.backoven.catdogshelter.domain.notice.command.application.service.NoticeLikeService;
import com.backoven.catdogshelter.domain.notice.command.application.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/notice-posts")
public class NoticeCommandController {

    private final NoticeService noticeService;
    private final NoticeLikeService noticeLikeService;

    @Autowired
    public NoticeCommandController(NoticeService noticeService, NoticeLikeService noticeLikeService) {
        this.noticeService = noticeService;
        this.noticeLikeService = noticeLikeService;
    }

    // JSON(dto) + 파일(files[])
    @Operation(summary = "게시글 등록", description = "관리자는 파일과 함께 게시글을 등록할 수 있다.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = {"/write"})
    public ResponseEntity<Long> create(
            @RequestPart("dto") NoticeDTO dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        long id = noticeService.writeNotice(dto, files);
        return ResponseEntity.ok(id);
    }

    // 수정: JSON(dto) + 새파일(newFiles[]) + 삭제할 파일 ID목록(deleteFileIds)
    @Operation(summary = "게시글 수정", description = "관리자는 파일과 함께 게시글을 수정할 수 있다.")
    @PutMapping(value = "/{id}/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestPart("dto") NoticeUpdateDTO dto,                       // title, content 만 포함
            @RequestPart(value = "newFiles", required = false) List<MultipartFile> newFiles,
            // 방법1) 같은 키를 여러 줄로 넣기 → deleteFileIds=1, deleteFileIds=3 ...
            // 방법2) JSON 배열로 넣기 → [1,3] (컨텐트 타입을 application/json으로)
            @RequestPart(value = "deleteFileIds", required = false) List<Integer> deleteFileIds
    ) {
        noticeService.modifyNotice(id, dto, newFiles, deleteFileIds);
        return ResponseEntity.noContent().build();
    }

    /**
     * 좋아요 토글 (userId 또는 headId 중 하나만 전송)
     * 예) /api/notices/10/likes/toggle?userId=3
     *    /api/notices/10/likes/toggle?headId=5
     */
    @Operation(summary = "게시글 추천", description = "일반회원과 보호소장은 게시물에 토글로 추천과 취소를 할 수 있다.")
    @PostMapping("/{noticeId}/likes/toggle")
    public ResponseEntity<Void> toggleLike(
            @PathVariable Integer noticeId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer headId
    ) {
        noticeService.toggleLike(noticeId, userId, headId);
        return ResponseEntity.ok().build();
    }
}
