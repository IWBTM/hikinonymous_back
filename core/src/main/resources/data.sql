INSERT INTO tb_code_master
(codeMaster, codeMasterNm, delYn)
VALUES
    ('MANAGER_STATUS', '관리자 상태', 'N'),
    ('MEMBER_STATUS', '회원 상태', 'N'),
    ('JOIN_TYPE', '가입 경로', 'N'),
    ('CRUD_TYPE', '기능', 'N'),
    ('BOARD_TYPE', '게시글 타입', 'N'),
    ('SERVICE_BOARD_TYPE', '서비스 게시글 타입', 'N'),
    ('INQUIRY_TYPE', '문의 타입', 'N'),
    ('BANNER_POSITION', '배너 위치', 'N'),
    ('DEVICE_TYPE', '디바이스 타입', 'N'),
    ('FILE_REF_TYPE', '파일 참조 타입', 'N'),
    ('ICON_TYPE', '아이콘 타입', 'N');

INSERT INTO tb_code
(codeMaster, code, codeNm, delYn, etc, sortOrder)
VALUES
    ('MANAGER_STATUS', 'ACTIVE', '활성화', 'N', '', 1),
    ('MANAGER_STATUS', 'UN_ACTIVE', '비활성화', 'N', '', 2),
    ('MANAGER_STATUS', 'STOP', '정지', 'N', '', 3),
    ('MEMBER_STATUS', 'ACTIVE', '활성화', 'N', '', 1),
    ('MEMBER_STATUS', 'UN_ACTIVE', '비활성화', 'N', '', 2),
    ('MEMBER_STATUS', 'DROP', '탈퇴', 'N', '', 3),
    ('JOIN_TYPE', 'EMAIL', '이메일', 'N', '', 1),
    ('JOIN_TYPE', 'KAKAO', '카카오', 'N', '', 2),
    ('JOIN_TYPE', 'NAVER', '네이버', 'N', '', 3),
    ('JOIN_TYPE', 'GOOGLE', '구글', 'N', '', 4),
    ('JOIN_TYPE', 'FACEBOOK', '페이스북', 'N', '', 5),
    ('CRUD_TYPE', 'C', '생성', 'N', '', 1),
    ('CRUD_TYPE', 'R', '읽기', 'N', '', 2),
    ('CRUD_TYPE', 'U', '수정', 'N', '', 3),
    ('CRUD_TYPE', 'D', '삭제', 'N', '', 4),

    ('BOARD_TYPE', 'BOARD', '게시글', 'N', '', 1),
    ('SERVICE_BOARD_TYPE', 'FAQ', 'FAQ', 'N', '', 1),
    ('SERVICE_BOARD_TYPE', 'NOTICE', '공지사항', 'N', '', 2),
    ('SERVICE_BOARD_TYPE', 'TERM', '이용 약관', 'N', '', 4),
    ('SERVICE_BOARD_TYPE', 'PRIVACY', '개인 정보 보호', 'N', '', 5),

    ('INQUIRY_TYPE', 'ONE', '1:1 문의', 'N', '', 1),
    ('INQUIRY_TYPE', 'REPORT', '신고', 'N', '', 2),
    ('INQUIRY_TYPE', 'DELETE', '삭제 요청', 'N', '', 3),
    ('INQUIRY_TYPE', 'ERROR', '오류/제안', 'N', '', 4),


    ('BANNER_POSITION', 'MAIN_TOP', '메인 탑', 'N', '', 1),

    ('DEVICE_TYPE', 'PC', '컴퓨터', 'N', '', 1),
    ('DEVICE_TYPE', 'MO', '휴대폰', 'N', '', 2),
    ('FILE_REF_TYPE', 'BANNER', '배너', 'N', '', 1),
    ('FILE_REF_TYPE', 'FAQ', 'FAQ', 'N', '', 2),

    ('ICON_TYPE', 'bx-abacus', '주판', 'N', 1),
    ('ICON_TYPE', 'bx-accessibility', '접근성', 'N', 2),
    ('ICON_TYPE', 'bx-add-to-queue', '대기열 추가', 'N', 3),
    ('ICON_TYPE', 'bx-adjust', '조정', 'N', 4),
    ('ICON_TYPE', 'bx-alarm', '알람', 'N', 5),
    ('ICON_TYPE', 'bx-alarm-add', '알람 추가', 'N', 6),
    ('ICON_TYPE', 'bx-alarm-exclamation', '알람 경고', 'N', 7),
    ('ICON_TYPE', 'bx-alarm-off', '알람 끄기', 'N', 8),
    ('ICON_TYPE', 'bx-alarm-snooze', '알람 스누즈', 'N', 9),
    ('ICON_TYPE', 'bx-album', '앨범', 'N', 10),
    ('ICON_TYPE', 'bx-align-justify', '양쪽 정렬', 'N', 11),
    ('ICON_TYPE', 'bx-align-left', '왼쪽 정렬', 'N', 12),
    ('ICON_TYPE', 'bx-align-middle', '가운데 정렬', 'N', 13),
    ('ICON_TYPE', 'bx-align-right', '오른쪽 정렬', 'N', 14),
    ('ICON_TYPE', 'bx-anchor', '닻', 'N', 15),
    ('ICON_TYPE', 'bx-angler', '낚시꾼', 'N', 16),
    ('ICON_TYPE', 'bx-aperture', '조리개', 'N', 17),
    ('ICON_TYPE', 'bx-arch', '아치', 'N', 18),
    ('ICON_TYPE', 'bx-archive', '보관함', 'N', 19),
    ('ICON_TYPE', 'bx-archive-in', '보관함 안', 'N', 20),
    ('ICON_TYPE', 'bx-archive-out', '보관함 밖', 'N', 21),
    ('ICON_TYPE', 'bx-area', '영역', 'N', 22),
    ('ICON_TYPE', 'bx-arrow-back', '뒤로 화살표', 'N', 23),
    ('ICON_TYPE', 'bx-arrow-from-bottom', '아래에서 화살표', 'N', 24),
    ('ICON_TYPE', 'bx-arrow-from-left', '왼쪽에서 화살표', 'N', 25),
    ('ICON_TYPE', 'bx-arrow-from-right', '오른쪽에서 화살표', 'N', 26),
    ('ICON_TYPE', 'bx-arrow-from-top', '위에서 화살표', 'N', 27),
    ('ICON_TYPE', 'bx-arrow-to-bottom', '아래로 화살표', 'N', 28),
    ('ICON_TYPE', 'bx-arrow-to-left', '왼쪽으로 화살표', 'N', 29),
    ('ICON_TYPE', 'bx-arrow-to-right', '오른쪽으로 화살표', 'N', 30),
    ('ICON_TYPE', 'bx-arrow-to-top', '위로 화살표', 'N', 31),
    ('ICON_TYPE', 'bx-at', '골뱅이', 'N', 32),
    ('ICON_TYPE', 'bx-atom', '원자', 'N', 33),
    ('ICON_TYPE', 'bx-award', '상', 'N', 34),
    ('ICON_TYPE', 'bx-badge', '배지', 'N', 35),
    ('ICON_TYPE', 'bx-badge-check', '배지 체크', 'N', 36),
    ('ICON_TYPE', 'bx-ball', '공', 'N', 37),
    ('ICON_TYPE', 'bx-bar-chart', '막대 차트', 'N', 38),
    ('ICON_TYPE', 'bx-bar-chart-alt', '막대 차트 대체', 'N', 39),
    ('ICON_TYPE', 'bx-bar-chart-alt-2', '막대 차트 대체 2', 'N', 40),
    ('ICON_TYPE', 'bx-bar-chart-square', '사각형 막대 차트', 'N', 41),
    ('ICON_TYPE', 'bx-barcode', '바코드', 'N', 42),
    ('ICON_TYPE', 'bx-barcode-reader', '바코드 리더기', 'N', 43),
    ('ICON_TYPE', 'bx-baseball', '야구', 'N', 44),
    ('ICON_TYPE', 'bx-basket', '바구니', 'N', 45),
    ('ICON_TYPE', 'bx-basketball', '농구', 'N', 46),
    ('ICON_TYPE', 'bx-bath', '목욕', 'N', 47),
    ('ICON_TYPE', 'bx-battery', '배터리', 'N', 48),
    ('ICON_TYPE', 'bx-battery-charging', '배터리 충전 중', 'N', 49),
    ('ICON_TYPE', 'bx-battery-full', '배터리 가득 참', 'N', 50),
    ('ICON_TYPE', 'bx-battery-low', '배터리 부족', 'N', 51),
    ('ICON_TYPE', 'bx-bed', '침대', 'N', 52),
    ('ICON_TYPE', 'bx-been-here', '여기 있었다', 'N', 53),
    ('ICON_TYPE', 'bx-beer', '맥주', 'N', 54),
    ('ICON_TYPE', 'bx-bell', '벨', 'N', 55),
    ('ICON_TYPE', 'bx-bell-minus', '벨 마이너스', 'N', 56),
    ('ICON_TYPE', 'bx-bell-off', '벨 꺼짐', 'N', 57),
    ('ICON_TYPE', 'bx-bell-plus', '벨 플러스', 'N', 58),
    ('ICON_TYPE', 'bx-bell-ring', '벨 울림', 'N', 59),
    ('ICON_TYPE', 'bx-bible', '성경', 'N', 60),
    ('ICON_TYPE', 'bx-bicycle', '자전거', 'N', 61),
    ('ICON_TYPE', 'bx-binoculars', '쌍안경', 'N', 62),
    ('ICON_TYPE', 'bx-block', '차단', 'N', 63),
    ('ICON_TYPE', 'bx-bluetooth', '블루투스', 'N', 64),
    ('ICON_TYPE', 'bx-body', '몸', 'N', 65),
    ('ICON_TYPE', 'bx-bold', '굵게', 'N', 66),
    ('ICON_TYPE', 'bx-bolt', '번개', 'N', 67),
    ('ICON_TYPE', 'bx-bomb', '폭탄', 'N', 68),
    ('ICON_TYPE', 'bx-bone', '뼈', 'N', 69),
    ('ICON_TYPE', 'bx-bong', '봉', 'N', 70),
    ('ICON_TYPE', 'bx-book', '책', 'N', 71),
    ('ICON_TYPE', 'bx-book-add', '책 추가', 'N', 72),
    ('ICON_TYPE', 'bx-book-alt', '대체 책', 'N', 73),
    ('ICON_TYPE', 'bx-book-bookmark', '책갈피 책', 'N', 74),
    ('ICON_TYPE', 'bx-book-content', '책 내용', 'N', 75),
    ('ICON_TYPE', 'bx-book-heart', '책 하트', 'N', 76),
    ('ICON_TYPE', 'bx-bookmark', '책갈피', 'N', 77),
    ('ICON_TYPE', 'bx-bookmark-alt', '대체 책갈피', 'N', 78),
    ('ICON_TYPE', 'bx-bookmark-alt-minus', '책갈피 마이너스', 'N', 79),
    ('ICON_TYPE', 'bx-bookmark-alt-plus', '책갈피 플러스', 'N', 80),
    ('ICON_TYPE', 'bx-bookmark-heart', '책갈피 하트', 'N', 81),
    ('ICON_TYPE', 'bx-bookmark-minus', '책갈피 마이너스', 'N', 82),
    ('ICON_TYPE', 'bx-bookmark-plus', '책갈피 플러스', 'N', 83),
    ('ICON_TYPE', 'bx-book-open', '열린 책', 'N', 84);

INSERT INTO tb_manager
(managerSeq, managerHp, managerId, managerNm, managerPwd, regDate, registerIp, useYn, managerStatus, superYn)
VALUES
    (1, 'R719IgEWymmC1fvTQhL9sQ==', 'VIeAmKaUOjQC+NluiKfNFNs4rLriXzxhoVo+acyj7hI=', '/343Z6ndRGyuiCfFsxJeIg==', 'ae91e71b28f1a443e2310875e54e5c6495a9af5dd9a827da933b3a66c9368a3e', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', 'Y', 1, 'Y'),
    (2, 'KPXT0vPRUX8nbu+JiA0Xvw==', 'BTgfA0bEHwCSM/EtCNf+1FxDAJlgtPOOxTFJ3CoOez8=', 'dIhFirIYfWIwxpRyuqZBgQ==', 'd310a2a7f77fb8a39a5b09dc405aac0c8ebfb5dc910aa4fca4c953182c1efb9b', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', 'Y', 1, 'N');

INSERT INTO tb_cms_menu
(menuCode, menuNm, displayYn, menuLevel, sortOrder, authDir, filePath, iconType, etc, delYn, registerIp, register, regDate)
VALUES
    ('ADMIN_MANAGEMENT', '관리자 관리', 'Y', 1, 1, 'admin', '', '관리자 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('MANAGER_MANAGEMENT', '관리자 관리', 'Y', 2, 1, 'admin', '/cms/admin/manager/list', '관리자 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('CMS_MENU_MANAGEMENT', '관리자 메뉴 관리', 'Y', 2, 2, 'admin', '/cms/admin/menu/list', '관리자 메뉴 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('ADMIN_AUTH_MANAGEMENT', '관리자 권한 관리', 'Y', 2, 3, 'admin', '/cms/admin/auth/list', '관리자 권한 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('SITE_MANAGEMENT', '사이트 관리', 'Y', 1, 2, 'site', '', '사이트 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('FRONT_MENU_MANAGEMENT', '사용자 메뉴 관리', 'Y', 2, 1, 'site', '/cms/site/frontMenu/list', '사용자 메뉴 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('BANNER_MANAGEMENT', '배너 관리', 'Y', 2, 2, 'site', '/cms/site/banner/list', '배너 메뉴 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('POPUP_MANAGEMENT', '팝업 관리', 'Y', 2, 3, 'site', '/cms/site/popup/list', '팝업 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('MEMBER_MANAGEMENT', '회원 관리', 'Y', 1, 3, 'member', '', '회원 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('MEMBER_MANAGEMENT', '회원 관리', 'Y', 2, 1, 'member', '/cms/member/member/list', '회원 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('DROP_MEMBER_MANAGEMENT', '탈퇴 회원 관리', 'Y', 2, 2, 'member', '/cms/member/drop/list', '탈퇴 회원 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('CATEGORY_MANAGEMENT', '카테고리 관리', 'Y', 1, 4, 'category', '', '카테고리 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('CATEGORY_MANAGEMENT', '카테고리 관리', 'Y', 2, 1, 'category', '/cms/category/category/list', '카테고리 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('BOARD_MANAGEMENT', '게시글 관리', 'Y', 1, 5, 'board', '', '게시글 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('BOARD_MANAGEMENT', '게시글 관리', 'Y', 2, 1, 'board', '/cms/board/board/list', '게시글 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('CODE_MANAGEMENT', '코드 관리', 'Y', 1, 6, 'code', '', '코드 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('CODE_MANAGEMENT', '코드 관리', 'Y', 2, 1, 'code', '/cms/code/code/list', '코드 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('TERM_MANAGEMENT', '약관 관리', 'Y', 1, 7, 'term', '', '약관 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('TERM_MANAGEMENT', '이용 약관 관리', 'Y', 2, 1, 'term', '/cms/term/term/list', '약관 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('PRIVACY_MANAGEMENT', '개인 정보 보호 관리', 'Y', 2, 2, 'term', '/cms/term/privacy/list', '개인 정보 보호 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('SERVICE_MANAGEMENT', '서비스 관리', 'Y', 1, 8, 'service', '', '서비스 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('FAQ_MANAGEMENT', 'FAQ 관리', 'Y', 2, 2, 'service', '/cms/service/faq/list', 'FAQ 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('NOTICE_MANAGEMENT', '공지사항 관리', 'Y', 2, 3, 'service', '/cms/service/notice/list', '공지사항 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('INQUIRY_MANAGEMENT', '문의 관리', 'Y', 1, 9, 'inquiry', '', '문의 관리 메뉴', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('INQUIRY_MANAGEMENT', '1:1 문의 관리', 'Y', 2, 1, 'inquiry', '/cms/inquiry/inquiry/list', '문의 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('REPORT_MANAGEMENT', '신고 관리', 'Y', 2, 1, 'inquiry', '/cms/inquiry/report/list', '신고 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('DELETE_MANAGEMENT', '삭제 요청 관리', 'Y', 2, 2, 'inquiry', '/cms/inquiry/delete/list', '삭제 요청 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S')),
    ('ERROR_MANAGEMENT', '오류/제안 관리', 'Y', 2, 3, 'inquiry', '/cms/inquiry/error/list', '오류/제안 관리', 'N', '127.0.0.1', 1, DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'));

-- gang.dev0nly@gmail.com
-- qwe123!!
insert into tb_member
(gender, memberDi, memberEmail, memberHp, memberName, memberNickName, memberPwd, privacyYn, receiveAdsEmailYn, regDate, registerIp, joinType, memberStatus, reportCnt)
values ('M', 'VIOSDJweoi', 'VIeAmKaUOjQC+NluiKfNFNs4rLriXzxhoVo+acyj7hI=', 'R719IgEWymmC1fvTQhL9sQ==', '/343Z6ndRGyuiCfFsxJeIg==', '갱', 'ae91e71b28f1a443e2310875e54e5c6495a9af5dd9a827da933b3a66c9368a3e', 'Y', 'Y', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', 7, 4, 0);

insert into tb_inquiry
(content, delYn, readYn, regDate, registerIp, title, inquiryType, register)
values
    ('내용 테스트 2', 'N', 'N', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', '제목 테스트 2', 21, 1),
    ('내용 테스트 1', 'N', 'N', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', '제목 테스트 1', 22, 1),
    ('내용 테스트 1', 'N', 'N', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', '제목 테스트 1', 23, 1),
    ('내용 테스트 1', 'N', 'N', DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), '127.0.0.1', '제목 테스트 1', 24, 1);




