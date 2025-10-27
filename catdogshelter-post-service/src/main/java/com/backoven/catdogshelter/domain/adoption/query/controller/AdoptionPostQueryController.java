package com.backoven.catdogshelter.domain.adoption.query.controller;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPageResponse;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.backoven.catdogshelter.domain.adoption.query.service.AdoptionPostQueryServiceImpl;
import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;

@RestController
@RequestMapping("/adoption-post")
@Tag(name="입양 게시글 조회 API")
public class AdoptionPostQueryController {
    private final AdoptionPostQueryServiceImpl adoptionService;

    @Autowired
    public AdoptionPostQueryController(AdoptionPostQueryServiceImpl adoptionService) {
        this.adoptionService = adoptionService;
    }
    // 게시판 목록 조회
    @Operation(summary = "게시판 목록 조회", description = "모든 입양 게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdoptionPostAllQueryDTO.class))))
    @GetMapping("/board")
    public ResponseEntity<?> findAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String sortType
    ){
        List<AdoptionPostAllQueryDTO> list =
                adoptionService.selectAdoptionAllPosts(page, size, sortType);

        long totalElements = adoptionService.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return ResponseEntity.ok(
                new AdoptionPageResponse<>(list, totalElements, totalPages, page + 1)
        );
    }
    // 게시글 조회
    @Operation(summary = "게시글 상세 조회", description = "게시글 ID로 특정 게시글 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdoptionPostDetailQueryDTO.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글")})
    @GetMapping("/{adoptionPostId}")
    public ResponseEntity<?> findPostById(@PathVariable int adoptionPostId){
        AdoptionPostDetailQueryDTO adoptionPostDetailDTO =
                adoptionService.selectAdoptionPostById(adoptionPostId);
        return ResponseEntity.ok(adoptionPostDetailDTO);
    }

    private final String UPLOAD_PATH = "/Users/haeone/Desktop/stack/be19-2nd-backoven-petShelter/catdogshelter-file-uploads/adoption";
    // 게시글 안에 파일조회
    @Operation(summary = "게시글 파일 조회", description = "게시글에 첨부된 이미지를 filename으로 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "파일 조회 성공",
                    content = @Content(mediaType = "image/jpeg")),
            @ApiResponse(responseCode = "404", description = "파일 없음")})
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> serveImageFile(
            @PathVariable String fileName) throws IOException {

        Path file = Paths.get("/Users/haeone/Desktop/stack/be19-2nd-backoven-petShelter/catdogshelter-file-uploads/adoption")
                .resolve(fileName);

        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


    // 조회순 게시판 목록 조회
    @Operation(summary = "조회순 게시판 목록 조회", description = "조회 수 기준으로 정렬된 게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdoptionPostAllQueryDTO.class))))
    @GetMapping("/board/view")
    public ResponseEntity<?> findPostByView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        List<AdoptionPostAllQueryDTO> list =
                adoptionService.selectAdoptionAllPosts(page, size, "view");

        long totalElements = adoptionService.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return ResponseEntity.ok(
                new AdoptionPageResponse<>(list, totalElements, totalPages, page + 1)
        );
    }
    // 추천순 게시판 목록 조회
    @Operation(summary = "추천순 게시판 목록 조회", description = "추천 수 기준으로 정렬된 게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdoptionPostAllQueryDTO.class))))
    public ResponseEntity<?> findPostByLiked(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        List<AdoptionPostAllQueryDTO> list =
                adoptionService.selectAdoptionAllPosts(page, size, "liked");

        long totalElements = adoptionService.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return ResponseEntity.ok(
                new AdoptionPageResponse<>(list, totalElements, totalPages, page + 1)
        );
    }
    // 키워드 검색 (title/content/breed 중 택1)
    @Operation(summary = "키워드 검색", description = "제목/내용/품종 중 하나의 키워드로 검색합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdoptionPostAllQueryDTO.class))))
    @GetMapping("/search/keyword")
    public ResponseEntity<?> findPostByKeyword(SearchCriteria criteria) {
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionPostByKeyword(criteria);
        return ResponseEntity.ok(adoptionPostList);
    }
    // 동물 조건 검색 (복수 조건 AND)
    @Operation(summary = "조건 검색", description = "동물 조건(종류, 나이, 성별 등)으로 복수 조건 검색 (AND 조건).")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdoptionPostAllQueryDTO.class))))
    @GetMapping("/search/condition")
    public ResponseEntity<?> findPostByAnimalCondition(SearchCriteria criteria) {
        List<AdoptionPostAllQueryDTO> adoptionPostList =
                adoptionService.selectAdoptionPostByAnimalCondition(criteria);
        return ResponseEntity.ok(adoptionPostList);
    }
}
