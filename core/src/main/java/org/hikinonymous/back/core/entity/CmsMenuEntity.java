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
@Entity(name = "tb_cms_menu")
@DynamicInsert
public class CmsMenuEntity {

    // 관리자 메뉴 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("관리자 메뉴 SEQ")
    private Long cmsMenuSeq;

    // 메뉴 코드
    @Column(nullable = false, length = 50)
    @Comment("메뉴 코드")
    private String menuCode;

    // 메뉴 이름
    @Column(nullable = false, length = 50)
    @Comment("메뉴 이름")
    private String menuNm;

    // 메뉴 레벨
    @Column(nullable = false)
    @Comment("메뉴 레벨")
    private Integer menuLevel;

    // 폴더
    @Column(nullable = false, length = 50)
    @Comment("폴더")
    private String authDir;

    // 파일 경로
    @Column(nullable = false, length = 255)
    @Comment("파일 경로")
    private String filePath;

    // 순서
    @Column(nullable = false)
    @Comment("순서")
    private Long sortOrder;

    // 설명
    @Column(length = 255)
    @Comment("설명")
    private String etc;

    // 화면 공개 여부
    @Column(nullable = false, length = 1)
    @Comment("화면 공개 여부")
    private String displayYn;

    // 삭제 여부
    @Column(nullable = false, length = 1)
    @ColumnDefault(value = "'N'")
    @Comment("삭제 여부")
    private String delYn;

    // 등록자
    @Comment("등록자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register", nullable = false)
    private ManagerEntity register;

    // 등록일
    @Column(nullable = false, length = 14)
    @Comment("등록일")
    private String regDate;

    // 등록자 IP
    @Column(nullable = false, length = 50)
    @Comment("등록자 IP")
    private String registerIp;

    // 수정자
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
