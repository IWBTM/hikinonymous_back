package org.hikinonymous.back.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_manager_auth")
@DynamicInsert
public class ManagerAuthEntity {

    // 관리자 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("관리자 권한 SEQ")
    private Long managerAuthSeq;

    // 관리자
    @Comment("관리자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager", nullable = false)
    private ManagerEntity managerEntity;

    // 메뉴
    @Comment("메뉴")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cmsMenu", nullable = false)
    private CmsMenuEntity cmsMenuEntity;

    // 권한
    @Comment("권한")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code", nullable = false)
    private CodeEntity codeEntity;

}
