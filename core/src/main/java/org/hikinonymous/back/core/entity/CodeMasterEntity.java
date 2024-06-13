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
@Entity(name = "tb_code_master")
@DynamicInsert
public class CodeMasterEntity {

    // 코드
    @Id
    @Column(nullable = false, length = 50)
    @Comment("코드")
    private String codeMaster;

    // 코드 이름
    @Column(nullable = false, length = 50)
    @Comment("코드 이름")
    private String codeMasterNm;

    // 삭제 여부
    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;
}
