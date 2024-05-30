INSERT INTO tb_code_master
(codeMasterSeq, codeMaster, codeMasterNm, delYn)
VALUES
    (1, 'MANAGER_STATUS', '관리자 상태', 'N'),
    (2, 'MEMBER_STATUS', '회원 상태', 'N'),
    (3, 'JOIN_TYPE', '가입 경로', 'N');

INSERT INTO tb_code
(codeSeq, codeMaster, code, codeNm, delYn, etc, sortOrder)
VALUES
    (1, 1, 'ACTIVE', '활성화', 'N', '', 1),
    (2, 1, 'UN_ACTIVE', '비활성화', 'N', '', 2),
    (3, 1, 'STOP', '정지', 'N', '', 3),
    (4, 2, 'ACTIVE', '활성화', 'N', '', 3),
    (5, 2, 'UN_ACTIVE', '비활성화', 'N', '', 3),
    (6, 2, 'WITHDRAWAL', '탈퇴', 'N', '', 3),
    (7, 3, 'EMAIL', '이메일', 'N', '', 3),
    (8, 3, 'KAKAO', '카카오', 'N', '', 3),
    (9, 3, 'NAVER', '네이버', 'N', '', 3),
    (10, 3, 'GOOGLE', '구글', 'N', '', 3),
    (11, 3, 'FACEBOOK', '페이스북', 'N', '', 3);

INSERT INTO tb_manager
(managerSeq, managerHp, managerId, managerNm, managerPwd, regDate, registerIp, useYn, managerStatus, superYn)
VALUES
    (1, 'R719IgEWymmC1fvTQhL9sQ==', 'VIeAmKaUOjQC+NluiKfNFNs4rLriXzxhoVo+acyj7hI=', '/343Z6ndRGyuiCfFsxJeIg==', 'ae91e71b28f1a443e2310875e54e5c6495a9af5dd9a827da933b3a66c9368a3e', DATE_FORMAT(NOW(), 'yyyymmddHHiiss'), '58.140.215.148', 'Y', 1, 'Y');