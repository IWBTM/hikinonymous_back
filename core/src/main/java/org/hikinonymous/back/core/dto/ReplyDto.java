package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "댓글 DTO"
)
@Data
public class ReplyDto extends CommonManagerDto {

    @Schema(description = "댓글 SEQ")
    private Long replySeq;

    @Schema(description = "댓글 내용")
    private String content;

}
