-- notice
INSERT INTO notice (title, content, created_at, updated_at, is_deleted, rating_id) VALUES
('서비스 점검 안내', '8월 20일 시스템 점검 예정', '2025-08-10', NULL, FALSE, -1),
('봉사 신청 마감 공지', '9월 봉사모집 마감되었습니다.', '2025-08-11', NULL, FALSE, -1),
('신규 기능 추가 안내', '이미지 업로드 기능 추가', '2025-08-12', NULL, FALSE, -1),
('입양 후기 이벤트', '입양 후기 이벤트 안내입니다.', '2025-08-13', NULL, FALSE, -1),
('긴급 공지', '보호소 정전으로 일시 중단됩니다.', '2025-08-14', NULL, FALSE, -1),
('9월 일정 안내', '9월 주요 일정 안내드립니다.', '2025-08-15', NULL, FALSE, -1),
('봉사왕 발표', '8월 봉사왕을 발표합니다.', '2025-08-16', NULL, FALSE, -1),
('정기 후원 감사 인사', '후원자분들께 감사드립니다.', '2025-08-17', NULL, FALSE, -1),
('사이트 이용약관 변경', '2025년 10월부터 적용됩니다.', '2025-08-18', NULL, FALSE, -1),
('보호소 모집 안내', '신규 보호소 등록 절차 안내', '2025-08-19', NULL, FALSE, -1);

-- noticeLiked
INSERT INTO noticeLiked (notice_id, user_id, head_id) VALUES
(1, NULL, 1),
(2, 2, NULL),
(3, NULL, 3),
(4, 4, NULL),
(5, NULL, 5),
(6, 6, NULL),
(7, NULL, 7),
(8, 8, NULL),
(9, NULL, 9),
(10, 10, NULL);

-- noticeFiles
INSERT INTO noticeFiles (file_rename, file_path, uploaded_at, notice_id) VALUES
('notice1.pdf', '/files/notice1.pdf', '2025-08-10', 1),
('notice2.pdf', '/files/notice2.pdf', '2025-08-11', 2),
('notice3.pdf', '/files/notice3.pdf', '2025-08-12', 3),
('notice4.pdf', '/files/notice4.pdf', '2025-08-13', 4),
('notice5.pdf', '/files/notice5.pdf', '2025-08-14', 5),
('notice6.pdf', '/files/notice6.pdf', '2025-08-15', 6),
('notice7.pdf', '/files/notice7.pdf', '2025-08-16', 7),
('notice8.pdf', '/files/notice8.pdf', '2025-08-17', 8),
('notice9.pdf', '/files/notice9.pdf', '2025-08-18', 9),
('notice10.pdf', '/files/notice10.pdf', '2025-08-19', 10);