package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "회원 DTO"
)
@Data
public class MemberUpdDto extends CommonManagerDto {

    @Schema(description = "회원 SEQ")
    private Long memberSeq;

    @Schema(description = "관리자 메모")
    private String memo;

    @Schema(description = "가입 타입 SEQ")
    private Long memberStatusSeq;

}
