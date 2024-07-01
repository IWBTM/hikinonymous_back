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
public class ManagerDto extends CommonDto {

    @Schema(description = "관리자 SEQ")
    private Long managerSeq;

    @Schema(description = "관리자 이메일")
    @Email
    @NotBlank(message = "관리자 이메일")
    private String managerId;

    @Schema(description = "관리자 이름")
    @NotBlank(message = "관리자 이름")
    private String managerNm;

    @Schema(description = "관리자 연락처")
    @NotBlank(message = "관리자 연락처")
    private String managerHp;

    @Schema(description = "관리자 비밀번호")
    private String managerPwd;

    @Schema(description = "슈퍼 관리자 여부")
    @NotBlank(message = "슈퍼 관리자 여부")
    private String superYn;

    private String lastPwdDate;

    private String lastLoginDate;

    private Integer loginCnt;

    private String loginFailCnt;

    @Schema(description = "관리자 상태")
    private String managerStatus;

    @Schema(description = "관리자 상태 SEQ")
    @NotNull(message = "관리자 상태 SEQ")
    private Long managerStatusSeq;

    @Schema(description = "사용 여부")
    @NotBlank(message = "사용 여부")
    private String useYn;

}
