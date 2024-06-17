package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(
        description = "관리자 메뉴 DTO"
)
@Data
public class CmsMenuDto extends CommonDto {

    @Schema(description = "관리자 메뉴 SEQ")
    @NotBlank(message = "cmsMenuSeq")
    private Long cmsMenuSeq;

    private String menuCode;

    private String menuNm;

    private Long menuLevel;

    private String authDir;

    private String filePath;

    private String etc;

    private String displayYn;

    private Long sortOrder;

    private String authTypes;

}
