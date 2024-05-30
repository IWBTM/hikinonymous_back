package org.hikinonymous.back.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    @NotBlank(message = "이메일")
    private String email;

    @NotBlank(message = "비밀번호")
    private String pwd;

}
