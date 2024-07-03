package org.hikinonymous.back.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_file_info")
@DynamicInsert
public class FileInfoEntity {

    // 파일 SEQ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("파일 SEQ")
    private Long fileInfoSeq;

    // 디바이스 구분
    @Comment("디바이스 구분")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceType", nullable = false)
    private CodeEntity deviceType;

    // 순서
    @Column
    @Comment("순서")
    private Integer sort;

    // 유형 구분
    @Comment("유형 구분")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refType", nullable = false)
    private CodeEntity refType;

    // 파일 경로
    @Column(nullable = false, length = 50)
    @Comment("파일 경로")
    private String filePath;

    // 파일 이름
    @Column(nullable = false, length = 255)
    @Comment("파일 이름")
    private String fileNm;

    // 파일 원래 이름
    @Column(nullable = false, length = 255)
    @Comment("파일 원래 이름")
    private String fileOriNm;

    // 파일 전체 경로
    @Column(nullable = false, length = 255)
    @Comment("파일 전체 경로")
    private String fileFullPath;

    // 파일 크기
    @Column(nullable = false)
    @Comment("파일 크기")
    private Long fileSize;

    // 파일 확장자
    @Column(nullable = false, length = 10)
    @Comment("파일 확장자")
    private String fileExt;

    // 설명
    @Column(length = 255)
    @Comment("설명")
    private String fileDesc;

    // 삭제 여부
    @Column(nullable = false, length = 1)
    @Comment("삭제 여부")
    @ColumnDefault(value = "'N'")
    private String delYn;

    // 다운로드 횟수
    @Column(nullable = false)
    @Comment("다운로드 횟수")
    @ColumnDefault(value = "0")
    private Integer downloadCnt;

}
