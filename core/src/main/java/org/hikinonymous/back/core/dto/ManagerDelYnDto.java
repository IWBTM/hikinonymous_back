package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(
        description = "관리자 DTO"
)
@Data
public class ManagerDelYnDto extends CommonDto {

    @Schema(
            description = "관리자 SEQ"
    )
    @NotBlank(message = "관리자 SEQ")
    private Long managerSeq;

    @Schema(
            description = "삭제 여부"
    )
    @NotBlank(message = "삭제 여부")
    private String delYn;

}
