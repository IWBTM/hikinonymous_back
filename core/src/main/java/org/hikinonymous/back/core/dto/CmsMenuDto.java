package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
        description = "관리자 메뉴 DTO"
)
@Data
public class CmsMenuDto extends CommonDto {

    @Schema(description = "관리자 메뉴 SEQ")
    private Long cmsMenuSeq;

    @Schema(description = "관리자 메뉴 코드")
    @NotBlank(message = "관리자 메뉴 코드")
    private String menuCode;

    @Schema(description = "관리자 메뉴 이름")
    @NotBlank(message = "관리자 메뉴 이름")
    private String menuNm;

    @Schema(description = "관리자 메뉴 레벨")
    @NotNull(message = "관리자 메뉴 레벨")
    private Long menuLevel;

    @Schema(description = "관리자 메뉴 그... 머라하지")
    @NotBlank(message = "그... 머라하지")
    private String authDir;

    @Schema(description = "관리자 메뉴 경로")
    private String filePath;

    @Schema(description = "설명")
    @NotBlank(message = "설명")
    private String etc;

    @Schema(description = "화면 표시 여부")
    @NotBlank(message = "화면 표시 여부")
    private String displayYn;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "정렬 순서")
    @NotNull(message = "정렬 순서")
    private Long sortOrder;

    private String authTypes;

}
