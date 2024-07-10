package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hikinonymous.back.core.entity.CategoryEntity;

@Schema(
        description = "게시글 DTO"
)
@Data
public class BoardDto extends CommonManagerDto {

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
