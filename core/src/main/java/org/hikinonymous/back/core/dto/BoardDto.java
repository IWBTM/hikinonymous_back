package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.MemberEntity;

@Schema(
        description = "게시글 DTO"
)
@Data
public class BoardDto extends CommonDto {

    @Schema(description = "게시글 SEQ")
    private Long boardSeq;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "카테고리")
    private CategoryEntity category;

    @Schema(description = "삭제 여부")
    @NotBlank(message = "삭제 여부")
    private String delYn;

}
