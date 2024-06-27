package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;

@Schema(
        description = "관리자 권한 DTO"
)
@Data
public class ManagerAuthDto extends CommonDto {

    @Schema(description = "관리자 권한 SEQ")
    @NotNull(message = "SEQ")
    private Long managerAuthSeq;

    @Schema(description = "관리자 SEQ")
    @NotBlank(message = "manager")
    private ManagerEntity manager;

    @Schema(description = "관리자 메뉴 SEQ")
    @NotBlank(message = "cmsMenu")
    private CmsMenuEntity cmsMenu;

    @Schema(description = "권한 (CRUD)")
    @NotBlank(message = "authTypes")
    private String authTypes;

}
