


USE CatDogShelter;

-- 시/도
INSERT INTO sido (sido_code, sido_name) VALUES
('11', '서울특별시'),
('26', '부산광역시'),
('27', '대구광역시'),
('28', '인천광역시'),
('29', '광주광역시'),
('30', '대전광역시'),
('31', '울산광역시'),
('41', '경기도'),
('42', '강원도'),
('43', '충청북도');

-- 시군구 (10개)
INSERT INTO sigungu (sigungu_code, sigungu_name, sido_id) VALUES
('11110', '종로구', 1),
('26110', '중구', 2),
('27110', '중구', 3),
('28110', '미추홀구', 4),
('29110', '동구', 5),
('30110', '서구', 6),
('31110', '남구', 7),
('41110', '수원시', 8),
('42110', '춘천시', 9),
('43110', '청주시', 10);

-- 등급 (10개)
INSERT INTO rating (id, name, standard) VALUES
(-1, '관리자', 0),
(0, '발자국', 0),
(1, '댕냥친구', 10),
(2, '댕냥지킴이', 30),
(3, '댕냥보호천사', 60),
(4, '슈퍼관리자', 0),
(5, '운영자', 0),
(6, '시스템', 0),
(7, '모니터링', 0),
(8, '테스터', 0);


-- 일반회원 (user) 10명
INSERT INTO user (user_name, user_account, user_password, e_mail, detail_address, user_phone,
                  cumulative_volunteer_time, month_volunteer_time, volunteer_count,
                  deactivation_times, user_status, activation_date, rating_id, sigungu_id)
VALUES
('박지수', 'parkjs', 'pass123!', 'parkjs@example.com', '서울 종로구 효자동 1길', '010-1111-1111', 0, 0, 0, 0, 'GENERAL', '2025-06-01', 0, 1),
('최민호', 'choimh', 'Qwe123!@#', 'choimh@example.com', '부산 중구 중앙대로 20', '010-2222-2222', 15, 3, 2, 0, 'GENERAL', '2025-05-01', 1, 2),
('이소영', 'leesy', 'Abcd1234$', 'leesy@example.com', '대구 중구 동성로 5', '010-3333-3333', 30, 10, 4, 1, 'GENERAL', '2025-05-05', 2, 3),
('김하진', 'kimhj', 'ZzYyXx123$', 'kimhj@example.com', '인천 미추홀구 학익동', '010-4444-4444', 60, 20, 8, 0, 'GENERAL', '2025-03-10', 3, 4),
('정다은', 'jungde', 'LoveDog1!', 'jungde@example.com', '광주 동구 충장로 12', '010-5555-5555', 100, 40, 10, 1, 'GENERAL', '2025-04-01', 3, 5),
('한유진', 'hanyj', 'Cat&Dog@25', 'hanyj@example.com', '대전 서구 둔산동', '010-6666-6666', 5, 1, 1, 0, 'GENERAL', '2025-02-14', 0, 6),
('오승환', 'oseh', 'P@ssSeoul9', 'oseh@example.com', '울산 남구 삼산로 23', '010-7777-7777', 40, 12, 6, 2, 'GENERAL', '2025-06-10', 2, 7),
('서민재', 'seominj', 'HappyDog#4', 'seominj@example.com', '경기 수원시 장안구 정자동', '010-8888-8888', 75, 22, 9, 3, 'GENERAL', '2025-05-20', 3, 8),
('이태훈', 'leeth', 'Sunshine!7', 'leeth@example.com', '강원 춘천시 중앙로', '010-9999-9999', 12, 4, 2, 0, 'GENERAL', '2025-07-15', 1, 9),
('박서윤', 'parksy', 'MoonCat$5', 'parksy@example.com', '충북 청주시 흥덕로', '010-1010-1010', 25, 6, 4, 1, 'GENERAL', '2025-07-05', 1, 10);

-- 보호소장 (shelter_head) 10명
INSERT INTO shelter_head
(head_account, head_password, e_mail, ceo_name, ceo_name2, head_phone,
 company_name, biz_number, cor_number, company_address, open_date, close_date, sigungu_id)
VALUES
('seoulshelter', 'Shelter2025!', 'seoul@example.com', '김영희', '박철수', '010-1111-2222',
 '서울사랑보호소', '111-22-33344', '990001-1234567', '서울 종로구 청운효자동 10', '2018-05-01', NULL, 1),
('busanhappy', 'HappyBusan#1', 'busan@example.com', '박정식', NULL, '010-2222-3333',
 '부산해피보호소', '222-33-44455', '880002-2345678', '부산 중구 중앙대로 50', '2019-03-15', NULL, 2),
('daegupet', 'PetCareDg9', 'daegu@example.com', '이민호', '정하늘', '010-3333-4444',
 '대구펫케어센터', '333-44-55566', '770003-3456789', '대구 중구 중앙로 20', '2020-07-01', NULL, 3),
('incheonhope', 'HopeCenter1', 'incheon@example.com', '정미경', NULL, '010-4444-5555',
 '인천희망보호소', '444-55-66677', '660004-4567890', '인천 미추홀구 주안로 100', '2017-11-11', NULL, 4),
('gwangjupet', 'Pet&CareGJ', 'gwangju@example.com', '한경숙', '서동우', '010-5555-6666',
 '광주펫앤케어센터', '555-66-77788', '550005-5678901', '광주 동구 금남로 12', '2016-04-20', NULL, 5),
('daejeonpaws', 'PawsDaejeon2', 'daejeon@example.com', '최영수', NULL, '010-6666-7777',
 '대전포우즈보호소', '666-77-88899', '440006-6789012', '대전 서구 둔산동 120', '2019-09-30', NULL, 6),
('ulsangood', 'GoodPetUS', 'ulsan@example.com', '이우람', NULL, '010-7777-8888',
 '울산굿펫센터', '777-88-99900', '330007-7890123', '울산 남구 번영로 8', '2021-02-10', NULL, 7),
('ggshelter', 'ShelterGG8', 'gg@example.com', '박준성', '이수현', '010-8888-9999',
 '경기동물쉼터', '888-99-00011', '220008-8901234', '수원시 장안구 고색동 45', '2020-12-01', NULL, 8),
('gwlove', 'LoveAnimalsGW', 'gw@example.com', '송현우', NULL, '010-9999-0000',
 '강원러브센터', '999-00-11122', '110009-9012345', '춘천시 중앙로 77', '2018-08-15', NULL, 9),
('chungbucare', 'CareCB5', 'cb@example.com', '류민지', '김태형', '010-1010-1111',
 '충북케어보호소', '101-12-21345', '660010-0123456', '청주시 상당구 흥덕로 22', '2017-06-06', NULL, 10);

-- 봉사왕 (volunteerKing) 10개
INSERT INTO volunteerKing (category, year, month, volunteer_time, user_id) VALUES
(FALSE, NULL, NULL, 150, 1),
(TRUE, 2025, 9, 30, 2),
(TRUE, 2025, 8, 45, 3),
(TRUE, 2025, 7, 25, 4),
(TRUE, 2025, 6, 55, 5),
(TRUE, 2025, 5, 20, 6),
(TRUE, 2025, 4, 65, 7),
(TRUE, 2025, 3, 70, 8),
(TRUE, 2025, 2, 40, 9),
(TRUE, 2025, 1, 35, 10);

-- 로그인 이력 (loginHistory) 10개
INSERT INTO loginHistory (ip_address, logged_at, user_id, head_id) VALUES
('203.0.113.1', '2025-09-01 08:00', 1, NULL),
('203.0.113.2', '2025-09-02 09:15', 2, NULL),
('203.0.113.3', '2025-09-03 10:25', 3, NULL),
('203.0.113.4', '2025-09-04 11:40', 4, NULL),
('203.0.113.5', '2025-09-05 12:30', 5, NULL),
('203.0.113.6', '2025-09-06 13:10', NULL, 1),
('203.0.113.7', '2025-09-07 14:15', NULL, 2),
('203.0.113.8', '2025-09-08 15:25', NULL, 3),
('203.0.113.9', '2025-09-09 16:45', NULL, 4),
('203.0.113.10', '2025-09-10 17:55', 6, NULL);

-- 메시지 (message) 10개
INSERT INTO message (content, created_at, status, subject_number,
                     subject_user_id, send_user_id, subject_head_id, send_head_id)
VALUES
('서울센터 봉사 신청 문의드립니다.', '2025-09-01 10:00', FALSE, 1, 1, 2, NULL, NULL),
('입양 절차 안내 부탁드립니다.', '2025-09-02 11:30', TRUE, 2, 2, 3, NULL, NULL),
('부산센터 위치 문의드립니다.', '2025-09-03 12:45', FALSE, 3, 3, NULL, 2, NULL),
('대구 보호소 관련 문의', '2025-09-04 13:00', TRUE, 4, 4, NULL, 3, NULL),
('후원 방법 알려주세요.', '2025-09-05 14:15', FALSE, 5, 5, NULL, 4, NULL),
('회원 탈퇴 후 재가입 문의드립니다.', '2025-09-06 15:20', TRUE, 6, 6, NULL, 5, NULL),
('봉사확인서 발급 요청합니다.', '2025-09-07 16:30', FALSE, 7, 7, NULL, 6, NULL),
('광주 보호소 현황이 궁금합니다.', '2025-09-08 17:45', FALSE, 8, NULL, NULL, 7, NULL),
('입양 가능한 반려동물 리스트 부탁드립니다.', '2025-09-09 18:00', TRUE, 9, NULL, NULL, 8, NULL),
('감사합니다! 잘 부탁드립니다.', '2025-09-10 19:20', FALSE, 10, NULL, NULL, 9, NULL);