package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "관리자 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerPwdDto extends CommonManagerDto {

    @Schema(description = "관리자 SEQ")
    @NotNull(message = "관리자 SEQ")
    private Long managerSeq;

    @Schema(description = "관리자 비밀번호")
    @NotBlank(message = "관리자 비밀번호")
    private String managerPwd;

}
