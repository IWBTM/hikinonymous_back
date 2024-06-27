package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
        description = "관리자 DTO"
)
@Data
public class ManagerPwdDto extends CommonDto {

    @Schema(description = "관리자 SEQ")
    @NotNull(message = "관리자 SEQ")
    private Long managerSeq;

    @Schema(description = "관리자 비밀번호")
    @NotBlank(message = "관리자 비밀번호")
    private String managerPwd;

}
