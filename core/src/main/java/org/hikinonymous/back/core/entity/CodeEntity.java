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
@Entity(name = "tb_code")
@DynamicInsert
public class CodeEntity {

    // 코드 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("코드 SEQ")
    private Long codeSeq;

    // 코드
    @Column(nullable = false, length = 50)
    @Comment("코드")
    private String code;

    // 코드 이름
    @Column(nullable = false, length = 50)
    @Comment("코드 이름")
    private String codeNm;

    @Comment("상위 코드")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codeMaster", nullable = false)
    private CodeMasterEntity codeMasterEntity;

    // 정렬 순서
    @Column(nullable = false)
    @Comment("정렬 순서")
    private Integer sortOrder;

    // 삭제 여부
    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    // 설명
    @Column(length = 255)
    @Comment("설명")
    private String etc;

}
