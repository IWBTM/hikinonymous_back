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
@Entity(name = "tb_inquiry")
@DynamicInsert
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("문의 SEQ")
    private Long inquirySeq;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "inquiry")
    private List<InquiryFileEntity> inquiryFile;

    @Column(nullable = false, length = 255)
    @Comment("제목")
    private String title;

    @Column(nullable = false, length = 255)
    @Comment("내용")
    private String content;

    @Comment("문의 타입")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inquiryType", nullable = false)
    private CodeEntity inquiryType;

    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    @Column(nullable = false, length = 1)
    @Comment("확인 여부")
    @ColumnDefault(value = "'N'")
    private String readYn;


    @Column(nullable = false, length = 1)
    @Comment("답장 여부")
    @ColumnDefault(value = "'N'")
    private String answerYn;

    @Column(length = 255)
    @Comment("답장")
    private String answer;

    @Comment("답장자")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answerer")
    private ManagerEntity answerer;

    @Column(length = 14)
    @Comment("답장일")
    private String answerDate;

    @Column(length = 50)
    @Comment("답장자 IP")
    private String answererIp;

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
