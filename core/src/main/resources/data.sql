INSERT INTO tb_code_master
(codeMasterSeq, codeMaster, codeMasterNm, delYn)
VALUES
    (1, 'MANAGER_STATUS', '관리자 상태', 'N'),
    (2, 'MENU_AUTH', '메뉴 권한', 'N'),
    (3, 'DEPARTMENT', '부서', 'N'),
    (4, 'POSITION', '직책', 'N'),
    (5, 'MEMBER_STATUS', '회원 상태', 'N'),
    (6, 'JOIN_TYPE', '가입 경로', 'N');

INSERT INTO tb_code
(codeSeq, codeMaster, code, codeNm, delYn, etc, sortOrder)
VALUES
    (1, 1, 'ACTIVE', '활성화', 'N', '', 1),
    (2, 1, 'UN_ACTIVE', '비활성화', 'N', '', 2),
    (3, 1, 'STOP', '정지', 'N', '', 3),
    (4, 3, 'DEV', '개발', 'N', '', 1),
    (5, 3, 'MARKETING', '마켓팅', 'N', '', 3),
    (6, 4, 'SUPER', '대표', 'N', '', 3),
    (7, 4, 'EMPLOYEE', '사원', 'N', '', 3),
    (8, 4, 'LEADER', '팀장', 'N', '', 3),
    (9, 5, 'ACTIVE', '활성화', 'N', '', 3),
    (10, 5, 'UN_ACTIVE', '비활성화', 'N', '', 3),
    (11, 5, 'WITHDRAWAL', '탈퇴', 'N', '', 3);
