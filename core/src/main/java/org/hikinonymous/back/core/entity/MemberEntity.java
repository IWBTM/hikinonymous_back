package org.hikinonymous.back.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_member")
@DynamicInsert
public class MemberEntity {

    // 회원 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 SEQ")
    private Long memberSeq;

    // 회원 이름 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("회원 이름 (암호화)")
    private String memberName;

    // 회원 별칭 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("회원 별칭 (암호화)")
    private String memberNickName;

    // 회원 이메일 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("회원 이메일 (암호화)")
    private String memberEmail;

    // 회원 비밀번호 (암호화)
    @Column(nullable = false, length = 255)
    @Comment("회원 비밀번호 (암호화)")
    private String memberPwd;

    // 회원 상태
    @Comment("회원 상태")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberStatus", nullable = false)
    private CodeEntity memberStatus;

    // 회원 DI (암호화)
    @Column(nullable = false, length = 255)
    @Comment("회원 DI (암호화)")
    private String memberDi;

    // 성별
    @Column(nullable = false, length = 1)
    @Comment("성별")
    private String gender;

    // 가입 경로
    @Comment("가입 경로")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "joinType", nullable = false)
    private CodeEntity joinType;

    // 약관 동의 여부
    @Column(nullable = false, length = 1)
    @Comment("약관 동의 여부")
    @ColumnDefault(value = "'Y'")
    private String privacyYn;

    // 신고 횟수
    @Column(nullable = false)
    @Comment("신고 횟수")
    private Integer reportCnt;

    // 이메일 수신 동의 여부
    @Column(nullable = false, length = 1)
    @Comment("이메일 수신 동의 여부")
    @ColumnDefault(value = "'Y'")
    private String receiveAdsEmailYn;

    // 마지막 로그인일
    @Column(nullable = false, length = 14)
    @Comment("마지막 로그인일")
    private String lastLoginDate;

    // 등록일
    @Column(nullable = false, length = 50)
    @Comment("등록일")
    private String regDate;

    // 등록자 IP
    @Column(nullable = false, length = 14)
    @Comment("등록자 IP")
    private String registerIp;

    // 수정일
    @Column(length = 50)
    @Comment("수정자 IP")
    private String updDate;

    // 수정자 IP
    @Column(length = 14)
    @Comment("수정일")
    private String updaterIp;

}
