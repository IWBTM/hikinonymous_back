package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;

@Schema(
        description = "관리자 메뉴 권한 리스트용 DTO"
)
@Data
@AllArgsConstructor
public class MenuAuthDto {

    @Schema(description = "관리자 메뉴명")
    private String menuNm;

    @Schema(description = "권한 (CRUD)")
    private String authTypes;

}
