package org.hikinonymous.back.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_inquiry_file")
@DynamicInsert
public class InquiryFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("문의 파일 SEQ")
    private Long inquiryFileSeq;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fileInfo", nullable = false)
    private FileInfoEntity fileInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inquiry", nullable = false)
    private InquiryEntity inquiry;

}
