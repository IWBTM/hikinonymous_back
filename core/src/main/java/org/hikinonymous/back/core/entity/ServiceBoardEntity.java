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
@Entity(name = "tb_service_board")
@DynamicInsert
public class ServiceBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게시글 SEQ")
    private Long serviceBoardSeq;

    @Column(nullable = false, length = 255)
    @Comment("제목")
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @Comment("내용")
    private String content;

    @Column(columnDefinition = "LONGTEXT")
    @Comment("요약")
    private String summary;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pcThumbImage")
    private FileInfoEntity pcThumbImage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moThumbImage")
    private FileInfoEntity moThumbImage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pcMainImage")
    private FileInfoEntity pcMainImage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moMainImage")
    private FileInfoEntity moMainImage;

    @Comment("서비스 게시글 타입")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceBoardType", nullable = false)
    private CodeEntity serviceBoardType;

    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    @Column(nullable = false, length = 1)
    @Comment("사용 여부")
    @ColumnDefault(value = "'N'")
    private String useYn;

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
