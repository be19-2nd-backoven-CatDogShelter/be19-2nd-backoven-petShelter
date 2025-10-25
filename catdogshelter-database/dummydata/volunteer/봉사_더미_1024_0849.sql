USE CatDogShelter;

-- 1. volunteerAssociation (10)
INSERT INTO volunteerAssociation (title, content, created_at, time, start_date, detail_address, deadline, number_of_people, is_end, head_id, sigungu_id) VALUES
('유기견 산책 봉사', '유기견들과 함께 산책하는 봉사활동입니다.', '2025-08-01', 3, '2025-08-10', '서울 종로구 공원길 10', TRUE, 10, TRUE, 1, 1),
('고양이 간식 나눔', '고양이 보호소에서 간식 급여 봉사', '2025-08-02', 2, '2025-08-11', '부산 중구 중앙로 12', TRUE, 8, TRUE, 2, 2),
('보호소 청소 봉사', '보호소 내외부 청소를 함께합니다.', '2025-08-03', 4, '2025-08-12', '대구 중구 청라로 23', TRUE, 12, TRUE, 3, 3),
('강아지 산책 및 목욕', '강아지 산책 후 목욕 봉사', '2025-08-04', 5, '2025-08-13', '인천 남구 학익동 78', TRUE, 7, TRUE, 4, 4),
('캣타워 청소 및 정리', '보호소 내부 정리 및 캣타워 청소', '2025-08-05', 3, '2025-08-14', '광주 동구 금남로 9', TRUE, 9, TRUE, 5, 5),
('유기견 산책 보조', '산책 중 관리 및 리드줄 점검', '2025-08-06', 2, '2025-08-15', '대전 동구 대전로 56', TRUE, 5, TRUE, 6, 6),
('유기동물 목욕 지원', '보호소 동물 목욕 및 드라이 보조', '2025-08-07', 4, '2025-08-16', '울산 중구 옥교동 23', TRUE, 6, TRUE, 7, 7),
('입양 홍보 캠페인', '입양 캠페인 부스 지원', '2025-08-08', 5, '2025-08-17', '경기 수원시 장안구 정자동', TRUE, 10, TRUE, 8, 8),
('고양이 건강검진 보조', '수의사 보조로 건강검진 지원', '2025-08-09', 2, '2025-08-18', '강원 춘천시 중앙로 22', TRUE, 8, TRUE, 9, 9),
('보호소 정기 점검', '시설 점검 및 보수 보조', '2025-08-10', 3, '2025-08-19', '충북 청주시 흥덕로 33', TRUE, 6, TRUE, 10, 10);

-- 2. volunteerAssociationFiles (10)
INSERT INTO volunteerAssociationFiles (file_rename, file_path, uploaded_at, association_id) VALUES
('va1_img1.jpg', '/files/va1_img1.jpg', '2025-08-01', 1),
('va2_img1.jpg', '/files/va2_img1.jpg', '2025-08-02', 2),
('va3_img1.jpg', '/files/va3_img1.jpg', '2025-08-03', 3),
('va4_img1.jpg', '/files/va4_img1.jpg', '2025-08-04', 4),
('va5_img1.jpg', '/files/va5_img1.jpg', '2025-08-05', 5),
('va6_img1.jpg', '/files/va6_img1.jpg', '2025-08-06', 6),
('va7_img1.jpg', '/files/va7_img1.jpg', '2025-08-07', 7),
('va8_img1.jpg', '/files/va8_img1.jpg', '2025-08-08', 8),
('va9_img1.jpg', '/files/va9_img1.jpg', '2025-08-09', 9),
('va10_img1.jpg', '/files/va10_img1.jpg', '2025-08-10', 10);

-- 3. volunteerAssociationApplicationDetails (10)
INSERT INTO volunteerAssociationApplicationDetails (status, time, volunteer_id, user_id) VALUES
(TRUE, 3, 1, 1),
(TRUE, 2, 2, 2),
(TRUE, 4, 3, 3),
(TRUE, 5, 4, 4),
(TRUE, 3, 5, 5),
(TRUE, 2, 6, 6),
(TRUE, 4, 7, 7),
(TRUE, 5, 8, 8),
(TRUE, 3, 9, 9),
(TRUE, 4, 10, 10);

-- 4. volunteerPost (10)
INSERT INTO volunteerPost (title, content, created_at, updated_at, view, is_blinded, is_deleted, volappdetail_id) VALUES
('첫 봉사 후기', '유기견들과 좋은 시간 보냈어요!', '2025-09-01', NULL, 50, FALSE, FALSE, 1),
('보람찬 하루', '보호소 환경이 정말 깨끗했어요.', '2025-09-02', NULL, 43, FALSE, FALSE, 2),
('감동적인 경험', '고양이들과 교감할 수 있었어요.', '2025-09-03', NULL, 66, FALSE, FALSE, 3),
('즐거운 봉사', '아이들과 산책하며 힐링했습니다.', '2025-09-04', NULL, 38, FALSE, FALSE, 4),
('뜻깊은 시간', '함께해서 더 즐거웠던 봉사활동!', '2025-09-05', NULL, 72, FALSE, FALSE, 5),
('감사한 하루', '보호소 직원분들 수고 많으셨어요.', '2025-09-06', NULL, 45, FALSE, FALSE, 6),
('산책 봉사 후기', '반려동물들이 너무 귀여웠어요!', '2025-09-07', NULL, 82, FALSE, FALSE, 7),
('고양이 봉사 소감', '청결 유지의 중요성을 느꼈어요.', '2025-09-08', NULL, 59, FALSE, FALSE, 8),
('팀워크 최고!', '다같이 협력해서 완수했어요.', '2025-09-09', NULL, 34, FALSE, FALSE, 9),
('봉사완료 후기', '좋은 인연을 만들었어요.', '2025-09-10', NULL, 90, FALSE, FALSE, 10);

-- 5. volunteerPostLiked (10, XOR user/head)
INSERT INTO volunteerPostLiked (post_id, user_id, head_id) VALUES
(1, 1, NULL),
(2, NULL, 2),
(3, 3, NULL),
(4, NULL, 4),
(5, 5, NULL),
(6, NULL, 6),
(7, 7, NULL),
(8, NULL, 8),
(9, 9, NULL),
(10, NULL, 10);

-- 6. volunteerPostReport (10, XOR user/head)
INSERT INTO volunteerPostReport (category, etc_detail, created_at, status, post_id, user_id, head_id) VALUES
('SPAM', NULL, '2025-09-02', FALSE, 1, 1, NULL),
('ABUSE', '욕설 포함', '2025-09-03', FALSE, 2, NULL, 2),
('ETC', '사진 품질 불량', '2025-09-04', FALSE, 3, 3, NULL),
('MISINFO', NULL, '2025-09-05', FALSE, 4, NULL, 4),
('SPAM', NULL, '2025-09-06', FALSE, 5, 5, NULL),
('FRAUD', '허위 내용 포함', '2025-09-07', FALSE, 6, NULL, 6),
('ETC', NULL, '2025-09-08', FALSE, 7, 7, NULL),
('ABUSE', NULL, '2025-09-09', FALSE, 8, NULL, 8),
('SPAM', NULL, '2025-09-10', FALSE, 9, 9, NULL),
('ETC', '기타 사유', '2025-09-11', FALSE, 10, NULL, 10);

-- 7. volunteerPostComment (10, XOR user/head)
INSERT INTO volunteerPostComment (content, created_at, updated_at, is_blinded, is_deleted, head_id, user_id, post_id) VALUES
('좋은 후기 감사합니다!', '2025-09-01', NULL, FALSE, FALSE, NULL, 1, 1),
('멋진 활동이네요!', '2025-09-02', NULL, FALSE, FALSE, 2, NULL, 2),
('감동이에요!', '2025-09-03', NULL, FALSE, FALSE, NULL, 3, 3),
('저도 참여하고 싶어요!', '2025-09-04', NULL, FALSE, FALSE, 4, NULL, 4),
('수고 많으셨어요!', '2025-09-05', NULL, FALSE, FALSE, NULL, 5, 5),
('이런 봉사 더 많아지면 좋겠어요.', '2025-09-06', NULL, FALSE, FALSE, 6, NULL, 6),
('따뜻한 후기네요.', '2025-09-07', NULL, FALSE, FALSE, NULL, 7, 7),
('읽으면서 힐링했어요.', '2025-09-08', NULL, FALSE, FALSE, 8, NULL, 8),
('좋은 경험 공유 감사해요.', '2025-09-09', NULL, FALSE, FALSE, NULL, 9, 9),
('감사합니다!', '2025-09-10', NULL, FALSE, FALSE, 10, NULL, 10);

-- 8. volunteerPostCommentReport (10, XOR user/head)
INSERT INTO volunteerPostCommentReport (category, etc_detail, created_at, status, head_id, user_id, comment_id) VALUES
('SPAM', NULL, '2025-09-02', FALSE, NULL, 1, 1),
('ABUSE', '비속어 포함', '2025-09-03', FALSE, 2, NULL, 2),
('FRAUD', NULL, '2025-09-04', FALSE, NULL, 3, 3),
('ETC', '의미없는 댓글', '2025-09-05', FALSE, 4, NULL, 4),
('MISINFO', NULL, '2025-09-06', FALSE, NULL, 5, 5),
('SPAM', NULL, '2025-09-07', FALSE, 6, NULL, 6),
('ABUSE', NULL, '2025-09-08', FALSE, NULL, 7, 7),
('ETC', NULL, '2025-09-09', FALSE, 8, NULL, 8),
('FRAUD', NULL, '2025-09-10', FALSE, NULL, 9, 9),
('SPAM', NULL, '2025-09-11', FALSE, 10, NULL, 10);

-- 9. volunteerPostFiles (10)
INSERT INTO volunteerPostFiles (file_rename, file_path, uploaded_at, post_id) VALUES
('vp1_img1.jpg', '/files/vp1_img1.jpg', '2025-09-01', 1),
('vp2_img1.jpg', '/files/vp2_img1.jpg', '2025-09-02', 2),
('vp3_img1.jpg', '/files/vp3_img1.jpg', '2025-09-03', 3),
('vp4_img1.jpg', '/files/vp4_img1.jpg', '2025-09-04', 4),
('vp5_img1.jpg', '/files/vp5_img1.jpg', '2025-09-05', 5),
('vp6_img1.jpg', '/files/vp6_img1.jpg', '2025-09-06', 6),
('vp7_img1.jpg', '/files/vp7_img1.jpg', '2025-09-07', 7),
('vp8_img1.jpg', '/files/vp8_img1.jpg', '2025-09-08', 8),
('vp9_img1.jpg', '/files/vp9_img1.jpg', '2025-09-09', 9),
('vp10_img1.jpg', '/files/vp10_img1.jpg', '2025-09-10', 10);