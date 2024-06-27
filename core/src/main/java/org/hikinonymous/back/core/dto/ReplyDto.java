package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.MemberEntity;

@Schema(
        description = "댓글 DTO"
)
@Data
public class ReplyDto extends CommonDto {

    @Schema(description = "댓글 SEQ")
    private Long replySeq;

    @Schema(description = "댓글 내용")
    private String content;

}
