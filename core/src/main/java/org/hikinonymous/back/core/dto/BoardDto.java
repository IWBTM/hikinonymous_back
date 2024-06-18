package org.hikinonymous.back.core.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.MemberEntity;

@Data
public class BoardDto extends CommonDto {

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

}
