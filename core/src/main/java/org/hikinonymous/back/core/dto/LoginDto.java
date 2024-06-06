package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(
        description = "로그인 DTO"
)
@Data
public class LoginDto {

    @Schema(
            description = "이메일"
    )
    @Email
    @NotBlank(message = "이메일")
    private String email;

    @Schema(
            description = "비밀번호"
    )
    @NotBlank(message = "비밀번호")
    private String pwd;

}
