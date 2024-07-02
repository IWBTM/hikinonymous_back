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
@Entity(name = "tb_banner")
@DynamicInsert
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("배너 SEQ")
    private Long bannerSeq;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pcImage", nullable = false)
    private FileInfoEntity pcImage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moImage", nullable = false)
    private FileInfoEntity moImage;

    @Column(nullable = false, length = 255)
    @Comment("제목")
    private String title;

    @Column(nullable = false, length = 255)
    @Comment("설명")
    private String etc;

    @Column(nullable = false, length = 255)
    @Comment("클릭 시 URL")
    private String url;

    @Comment("위치")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position", nullable = false)
    private CodeEntity position;

    @Column(nullable = false, length = 1)
    @Comment("사용 여부")
    private String useYn;

    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    // 등록자
    @Comment("등록자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register", nullable = false)
    private MemberEntity register;

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
    private MemberEntity updater;

    // 수정일
    @Column(length = 14)
    @Comment("수정일")
    private String updDate;

    // 수정자 IP
    @Column(length = 50)
    @Comment("수정자 IP")
    private String updaterIp;
}
