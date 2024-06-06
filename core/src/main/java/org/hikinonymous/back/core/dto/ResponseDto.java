package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "응답 DTO"
)
@Data
public class ResponseDto {

    @Schema(
            description = "응답 상태 코드",
            example = "200"
    )
    private int code;

    @Schema(
            description = "응답 상태 메시지",
            example = "성공  하였습니다."
    )
    private String message;

    @Schema(
            description = "응답 데이터",
            nullable = true
    )
    private Object data;

}
