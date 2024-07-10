package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "서비스 게시글 DTO"
)
@Data
public class ServiceBoardDelYnDto extends CommonManagerDto {

    @Schema(description = "게시글 SEQ")
    private Long serviceBoardSeq;

    @Schema(description = "삭제 여부")
    private String delYn;

}
