INSERT INTO tb_code_master
(codeMaster, codeMasterNm, delYn)
VALUES
    ('MANAGER_STATUS', '관리자 상태', 'N'),
    ('MEMBER_STATUS', '회원 상태', 'N'),
    ('JOIN_TYPE', '가입 경로', 'N'),
    ('CRUD_TYPE', '기능', 'N');

INSERT INTO tb_code
(codeMaster, code, codeNm, delYn, etc, sortOrder)
VALUES
    ('MANAGER_STATUS', 'ACTIVE', '활성화', 'N', '', 1),
    ('MANAGER_STATUS', 'UN_ACTIVE', '비활성화', 'N', '', 2),
    ('MANAGER_STATUS', 'STOP', '정지', 'N', '', 3),
    ('MEMBER_STATUS', 'ACTIVE', '활성화', 'N', '', 1),
    ('MEMBER_STATUS', 'UN_ACTIVE', '비활성화', 'N', '', 2),
    ('MEMBER_STATUS', 'WITHDRAWAL', '탈퇴', 'N', '', 3),
    ('JOIN_TYPE', 'EMAIL', '이메일', 'N', '', 1),
    ('JOIN_TYPE', 'KAKAO', '카카오', 'N', '', 2),
    ('JOIN_TYPE', 'NAVER', '네이버', 'N', '', 3),
    ('JOIN_TYPE', 'GOOGLE', '구글', 'N', '', 4),
    ('JOIN_TYPE', 'FACEBOOK', '페이스북', 'N', '', 5),
    ('CRUD_TYPE', 'C', '생성', 'N', '', 1),
    ('CRUD_TYPE', 'R', '읽기', 'N', '', 2),
    ('CRUD_TYPE', 'U', '수정', 'N', '', 3),
    ('CRUD_TYPE', 'D', '삭제', 'N', '', 4);

INSERT INTO tb_manager
(managerSeq, managerHp, managerId, managerNm, managerPwd, regDate, registerIp, useYn, managerStatus, superYn)
VALUES
    (1, 'R719IgEWymmC1fvTQhL9sQ==', 'VIeAmKaUOjQC+NluiKfNFNs4rLriXzxhoVo+acyj7hI=', '/343Z6ndRGyuiCfFsxJeIg==', 'ae91e71b28f1a443e2310875e54e5c6495a9af5dd9a827da933b3a66c9368a3e', DATE_FORMAT(NOW(), 'yyyymmddHHiiss'), '58.140.215.148', 'Y', 1, 'Y'),
    (2, 'KPXT0vPRUX8nbu+JiA0Xvw==', 'BTgfA0bEHwCSM/EtCNf+1FxDAJlgtPOOxTFJ3CoOez8=', 'dIhFirIYfWIwxpRyuqZBgQ==', 'd310a2a7f77fb8a39a5b09dc405aac0c8ebfb5dc910aa4fca4c953182c1efb9b', DATE_FORMAT(NOW(), 'yyyymmddHHiiss'), '58.140.215.148', 'Y', 1, 'N');

INSERT INTO tb_cms_menu
(menuCode, menuNm, displayYn, menuLevel, sortOrder, authDir, filePath, etc, registerIp, register, regDate)
VALUES
    ('ADMIN_MANAGEMENT', '관리자 관리', 'Y', 1, 1, 'admin', '', '관리자 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('ADMIN_MANAGEMENT', '관리자 관리', 'Y', 2, 1, 'admin', '/cms/admin/admin/list', '관리자 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('CMS_MENU_MANAGEMENT', '관리자 메뉴 관리', 'Y', 2, 2, 'admin', '/cms/admin/menu/list', '관리자 메뉴 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('ADMIN_AUTH_MANAGEMENT', '관리자 권한 관리', 'Y', 2, 3, 'admin', '/cms/admin/auth/list', '관리자 권한 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('SITE_MANAGEMENT', '사이트 관리', 'Y', 1, 2, 'site', '', '사이트 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('FRONT_MENU_MANAGEMENT', '사용자 메뉴 관리', 'Y', 2, 1, 'site', '/cms/site/frontMenu/list', '사용자 메뉴 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('BANNER_MANAGEMENT', '배너 관리', 'Y', 2, 2, 'site', '/cms/site/banner/list', '배너 메뉴 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('POPUP_MANAGEMENT', '팝업 관리', 'Y', 2, 3, 'site', '/cms/site/popup/list', '팝업 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('INQUIRY_MANAGEMENT', '문의 관리', 'Y', 2, 4, 'site', '/cms/site/inquiry/list', '문의 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('FAQ_MANAGEMENT', 'FAQ 관리', 'Y', 2, 5, 'site', '/cms/site/faq/list', 'FAQ 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('NOTICE_MANAGEMENT', '공지사항 관리', 'Y', 2, 6, 'site', '/cms/site/notice/list', '공지사항 관리', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('MEMBER_MANAGEMENT', '회원 관리', 'Y', 1, 3, 'member', '', '회원 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('MEMBER_MANAGEMENT', '회원 관리', 'Y', 2, 1, 'member', '/cms/member/member/list', '회원 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('DROP_MEMBER_MANAGEMENT', '탈퇴 회원 관리', 'Y', 2, 2, 'member', '/cms/member/dropMember/list', '탈퇴 회원 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('CATEGORY_MANAGEMENT', '카테고리 관리', 'Y', 1, 4, 'category', '', '카테고리 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('CATEGORY_MANAGEMENT', '카테고리 관리', 'Y', 2, 1, 'category', '/cms/category/category/list', '카테고리 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('BOARD_MANAGEMENT', '게시글 관리', 'Y', 1, 5, 'board', '', '게시글 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('BOARD_MANAGEMENT', '게시글 관리', 'Y', 2, 1, 'board', '/cms/board/board/list', '게시글 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('CODE_MANAGEMENT', '코드 관리', 'Y', 1, 6, 'code', '', '코드 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('CODE_MANAGEMENT', '코드 관리', 'Y', 2, 1, 'code', '/cms/code/code/list', '코드 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),

    ('TERM_MANAGEMENT', '약관 관리', 'Y', 1, 7, 'term', '', '약관 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('TERM_MANAGEMENT', '이용 약관 관리', 'Y', 2, 1, 'term', '/cms/term/term/list', '약관 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss')),
    ('PRIVACY_MANAGEMENT', '개인 정보 보호 관리', 'Y', 2, 1, 'term', '/cms/term/privacy/list', '개인 정보 보호 관리 메뉴', '58.140.215.148', 1, DATE_FORMAT(NOW(), 'yyyyMMddHHiiss'));

INSERT INTO tb_manager_auth
(cmsMenu, manager, authTypes)
VALUES
    (13, 2, 'R'),
    (15, 2, 'R'),
    (17, 2, 'RC');
