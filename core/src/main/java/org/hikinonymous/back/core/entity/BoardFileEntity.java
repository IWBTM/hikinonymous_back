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
@Entity(name = "tb_board_file")
@DynamicInsert
public class BoardFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("문의 파일 SEQ")
    private Long boardFileSeq;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fileInfo", nullable = false)
    private FileInfoEntity fileInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board", nullable = false)
    private BoardEntity board;

}
