package org.hikinonymous.back.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_manager")
@DynamicInsert
public class ManagerEntity {

    // 관리자 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("관리자 SEQ")
    private Long managerSeq;

    // 관리자 ID (암호화)
    @Column(nullable = false, length = 255)
    @Comment("관리자 ID (암호화)")
    private String managerId;

    // 관리자 PWD (암호화)
    @Column(nullable = false, length = 255)
    @Comment("관리자 PWD (암호화)")
    private String managerPwd;

    // 관리자 이름 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("관리자 이름 (암호화)")
    private String managerNm;

    // 관리자 연락처 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("관리자 연락처 (암호화)")
    private String managerHp;

    // 로그인 횟수
    @Column(nullable = false)
    @Comment("로그인 횟수")
    @ColumnDefault(value = "0")
    private Integer loginCnt;

    // 로그인 실패 횟수
    @Column(nullable = false)
    @Comment("로그인 실패 횟수")
    @ColumnDefault(value = "0")
    private Integer loginFailCnt;

    // 관리자 상태
    @Comment("관리자 상태")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "managerStatus", nullable = false)
    private CodeEntity managerStatus;

    // 사용 여부
    @Column(nullable = false, length = 1)
    @Comment("사용 여부")
    private String useYn;

    // 삭제 여부
    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    // 슈퍼 관리자 여부
    @Comment("슈퍼 관리자")
    @Column(nullable = false, length = 1)
    @ColumnDefault(value = "'N'")
    private String superYn;

    // 마지막 로그인일
    @Column(length = 14)
    @Comment("마지막 로그인일")
    private String lastLoginDate;

    // 마지막 비밀번호 변경일
    @Column(length = 14)
    @Comment("마지막 비밀번호 변경일")
    private String lastPwdDate;

        // 등록일
    @Column(nullable = false, length = 14)
    @Comment("등록일")
    private String regDate;

    // 등록자 IP
    @Column(nullable = false, length = 50)
    @Comment("등록자 IP")
    private String registerIp;

    @Comment("수정자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updater")
    private ManagerEntity updater;

    // 수정일
    @Column(length = 14)
    @Comment("수정일")
    private String updDate;

    // 수정자 IP
    @Column(length = 50)
    @Comment("수정자 IP")
    private String updaterIp;
}
