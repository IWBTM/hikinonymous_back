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
@Entity(name = "tb_board")
@DynamicInsert
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게시글 SEQ")
    private Long boardSeq;

    @Column(nullable = false, length = 255)
    @Comment("제목")
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @Comment("내용")
    private String content;

    @Comment("카테고리")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", nullable = false)
    private CategoryEntity category;

    @Comment("게시글 타입")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardType", nullable = false)
    private CodeEntity boardType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "board")
    private List<ReplyEntity> replies;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "board")
    private List<BoardFileEntity> boardFiles;

    @Column(nullable = false)
    @Comment("조회수")
    private Long viewCnt;

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
