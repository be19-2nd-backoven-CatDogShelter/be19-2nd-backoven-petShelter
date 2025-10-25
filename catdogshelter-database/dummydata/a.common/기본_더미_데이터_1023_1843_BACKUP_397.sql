use CatDogShelter;

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

-- 시/군/구
INSERT INTO sigungu (sigungu_code, sigungu_name, sido_id) VALUES
('11110', '종로구', 1),
('11140', '중구', 1),
('11170', '용산구', 1),
('11200', '성동구', 1),
('11215', '광진구', 1),
('11230', '동대문구', 1),
('11260', '중랑구', 1),
('11290', '성북구', 1),
('11305', '강북구', 1),
('11320', '도봉구', 1),
('26110', '중구', 2),
('26140', '서구', 2),
('26170', '동구', 2),
('26200', '영도구', 2),
('26230', '부산진구', 2),
('27110', '중구', 3),
('27140', '동구', 3),
('27170', '서구', 3),
('28110', '중구', 4),
('28140', '동구', 4),
('28170', '남구', 4),
('29110', '동구', 5),
('30110', '동구', 6),
('31110', '중구', 7),
('41110', '수원시', 8),
('41111', '수원시 장안구', 8),
('41113', '수원시 권선구', 8),
('42110', '춘천시', 9),
('43110', '청주시', 10);

-- 등급 (rating)
INSERT INTO rating (id, name, standard) VALUES
(-1, '관리자', 0),
(0, '발자국', 0),
(1, '댕냥친구', 10),
(2, '댕냥지킴이', 30),
(3, '댕냥보호천사', 60),
(4, '관리자', 0),
(5, '슈퍼관리자', 0),
(6, '시스템', 0),
(7, '모니터링', 0),
(8, '운영자', 0);


<<<<<<< HEAD:catdogshelter-database/dummydata/a.common/CatDogShelter_common_dummy_data_.sql
-- 일반회원 (user) 10명
INSERT INTO user (user_name, user_account, user_password, e_mail, detail_address, answer, user_phone,
                  cumulative_volunteer_time, month_volunteer_time, volunteer_count,
                  deactivation_times, user_status, activation_date, rating_id, sigungu_id, questionCategory_id) VALUES
('admin', 'admin', '$2a$10$obCrYsPYbr.mZc78lyGuJOpgViEqOilarQTnfR/InamU5f7rQ565q', 'admin@petShelter.com', '서울시 강남구 테헤란로 123', '010-1234-5678',
 0, 0, 0, 0, 'GENERAL', '2025-06-01', -1, 1, 1),
=======
-- 일반회원
INSERT INTO user (user_name, user_account, user_password, e_mail, detail_address, user_phone, cumulative_volunteer_time, month_volunteer_time, volunteer_count, deactivation_times, user_status, activation_date, rating_id, sigungu_id) VALUES
('박지수', 'parkjs', 'pass123!', 'parkjs@example.com', '서울 종로구 한옥마을길 5', '010-1234-5678', 0, 0, 0, 0, 'GENERAL', '2025-06-01', 0, 1),
('최민호', 'choiminho', 'Qwe123!@#', 'minho@example.com', '서울 중구 을지로 10', '010-2345-6789', 15, 5, 3, 1, 'GENERAL', '2025-05-15', 1, 2),
('이소영', 'leesy', 'Abcd987$', 'soyoung@example.com', '부산 서구 구덕로 23', '010-3456-7890', 30, 10, 5, 2, 'GENERAL', '2025-04-20', 2, 6),
('김하진', 'kimhj', 'ZzYyXx123$', 'hajin@example.com', '대구 중구 산격로 45', '010-4567-8901', 65, 20, 8, 3, 'BLACK', '2025-03-10', 3, 16),
('정다은', 'jeongde', 'LoveDogs1!', 'daeun@example.com', '인천 남구 학익동 78', '010-5678-9012', 100, 30, 12, 4, 'CANCEL', '2025-02-01', 3, 19),
('한유진', 'hanyj', 'Cat&Dog@2025', 'yujin@example.com', '광주 동구 금남로 2가 34', '010-6789-0123', 5, 1, 1, 0, 'GENERAL', '2025-07-01', 0, 22),
('오승환', 'oseungh', 'P@ssSeoul9', 'seungh@example.com', '대전 동구 대전로 56', '010-7890-1234', 40, 12, 6, 2, 'GENERAL', '2025-06-10', 2, 6),
('서민재', 'seominj', 'HappyDog#4', 'seominj@example.com', '울산 중구 옥교동 101', '010-8901-2345', 75, 22, 9, 3, 'GENERAL', '2025-05-20', 3, 7),
('이태훈', 'leeth', 'Sunshine!7', 'leeth@example.com', '경기 수원시 장안구 정자동 8', '010-9012-3456', 12, 4, 2, 0, 'GENERAL', '2025-07-15', 1, 8),
('박서윤', 'parksy', 'MoonCat$5', 'parksy@example.com', '강원 춘천시 중앙로 12', '010-0123-4567', 25, 6, 4, 1, 'GENERAL', '2025-07-05', 1, 9);
>>>>>>> devlop:catdogshelter-database/dummydata/a.common/기본_더미_데이터_1023_1843.sql

-- 봉사왕
INSERT INTO volunteerKing (category, year, month, volunteer_time, user_id) VALUES
(FALSE, NULL, NULL, 150, 4),
(TRUE, 2025, 9, 30, 2),
(TRUE, 2025, 8, 50, 3),
(TRUE, 2025, 7, 25, 6),
(TRUE, 2025, 9, 60, 8),
(TRUE, 2025, 5, 45, 7),
(TRUE, 2025, 6, 55, 9),
(TRUE, 2025, 4, 20, 10),
(TRUE, 2025, 3, 35, 1),
(TRUE, 2025, 2, 40, 5);

-- 보호소장
INSERT INTO shelter_head (head_account, head_password, e_mail, ceo_name, ceo_name2, head_phone, company_name, biz_number, cor_number, company_address, open_date, close_date, sigungu_id) VALUES
('seoulsarang', 'Shelter2025!', 'seoul@example.com', '김영희', '박철수', '010-1111-1111', '서울사랑보호소', '111-22-33344', '990001-1234567', '서울 종로구 청운효자동 10', '2018-05-01', NULL, 1),
('busanhappy', 'HappyBusan#1', 'busan@example.com', '박정식', NULL, '010-2222-2222', '부산해피보호소', '222-33-44455', '880002-2345678', '부산 중구 중앙대로 50', '2019-03-15', NULL, 2),
('daegupet', 'PetCareDg9', 'daegu@example.com', '이민호', '정하늘', '010-3333-3333', '대구펫케어센터', '333-44-55566', '770003-3456789', '대구 중구 중앙로 20', '2020-07-01', NULL, 3),
('incheonhope', 'HopeCenter1', 'incheon@example.com', '정미경', NULL, '010-4444-4444', '인천희망보호소', '444-55-66677', '660004-4567890', '인천 남구 주안로 100', '2017-11-11', NULL, 4),
('gwangjupet', 'Pet&CareGJ', 'gwangju@example.com', '한경숙', '서동우', '010-5555-5555', '광주펫앤케어센터', '555-66-77788', '550005-5678901', '광주 동구 금남로 12', '2016-04-20', NULL, 5),
('daejeonpaws', 'PawsDaejeon2', 'daejeon@example.com', '최영수', NULL, '010-6666-6666', '대전포우즈보호소', '666-77-88899', '440006-6789012', '대전 동구 대전로 33', '2019-09-30', NULL, 6),
('ulsangoodpet', 'GoodPetUS', 'ulsan@example.com', '이우람', NULL, '010-7777-7777', '울산굿펫센터', '777-88-99900', '330007-7890123', '울산 중구 번영로 8', '2021-02-10', NULL, 7),
('ggshelter', 'ShelterGG8', 'gg@example.com', '박준성', '이수현', '010-8888-8888', '경기동물쉼터', '888-99-00011', '220008-8901234', '수원시 장안구 고색동 45', '2020-12-01', NULL, 8),
('gwanlove', 'LoveAnimalsGW', 'gw@example.com', '송현우', NULL, '010-9999-9999', '강원러브센터', '999-00-11122', '110009-9012345', '춘천시 중앙로 77', '2018-08-15', NULL, 9),
('chungbucare', 'CareCB5', 'cb@example.com', '류민지', '김태형', '010-1010-1010', '충북케어보호소', '101-12-21345', '660010-0123456', '청주시 상당구 흥덕로 22', '2017-06-06', NULL, 10);


-- 로그인 이력
INSERT INTO loginHistory (ip_address, logged_at, user_id, head_id) VALUES
('203.0.113.1', '2025-09-01 08:00', 1, NULL),
('203.0.113.2', '2025-09-02 09:15', 2, NULL),
('203.0.113.3', '2025-09-03 18:45', 3, NULL),
('203.0.113.4', '2025-09-04 20:00', 4, NULL),
('203.0.113.5', '2025-09-05 07:30', 5, NULL),
('203.0.113.6', '2025-09-06 12:20', NULL, 1),
('203.0.113.7', '2025-09-07 14:55', NULL, 2),
('203.0.113.8', '2025-09-08 16:30', NULL, 3),
('203.0.113.9', '2025-09-09 11:10', 6, NULL),
('203.0.113.10','2025-09-10 19:50', NULL, 4);

-- 메세지
INSERT INTO message (content, created_at, status, subject_number,
                     subject_user_id, send_user_id, subject_head_id, send_head_id) VALUES
('서울센터 봉사 신청 문의드립니다.', '2025-09-01 10:00', FALSE, 1, 1, 2, NULL, NULL),
('보호소 입양 절차 안내 부탁드립니다.', '2025-09-02 11:30', TRUE, 2, 2, 3, NULL, NULL),
('부산센터 위치 문의드립니다.', '2025-09-03 12:45', FALSE, 3, 3, NULL, 2, NULL), 
('대구 보호소 관련 문의', '2025-09-04 13:00', TRUE, 4, 4, NULL, 3, NULL),
('후원 방법 알려주세요.', '2025-09-05 14:15', FALSE, 5, 5, NULL, 1, NULL),
('회원 탈퇴 후 재가입 문의드립니다.', '2025-09-06 15:20', TRUE, 6, NULL, NULL, 4, NULL),
('봉사확인서 발급 요청합니다.', '2025-09-07 16:30', FALSE, 7, 1, NULL, NULL, NULL),
('광주 보호소 현황이 궁금합니다.', '2025-09-08 17:45', FALSE, 8, NULL, NULL, 5, NULL),
('입양 가능한 반려동물 리스트 부탁드립니다.', '2025-09-09 18:00', TRUE, 9, NULL, NULL, 6, NULL),
('감사합니다! 잘 부탁드립니다.', '2025-09-10 19:20', FALSE, 10, NULL, NULL, 7, NULL);