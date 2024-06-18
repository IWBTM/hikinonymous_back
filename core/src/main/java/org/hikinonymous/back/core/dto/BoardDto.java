package org.hikinonymous.back.core.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.MemberEntity;

@Data
public class BoardDto {

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

    // 수정자
    @Comment("수정자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updater")
    private MemberEntity updater;

    // 수정일
    @Column(length = 14)
    @Comment("수정일")
    private String updDate;

}
