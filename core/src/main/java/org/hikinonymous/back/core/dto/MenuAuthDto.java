package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;

import java.io.Serializable;

@Schema(
        description = "관리자 메뉴 권한 리스트용 DTO"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAuthDto implements Serializable {

    @Schema(description = "관리자 메뉴 권한 SEQ")
    private Long managerAuthSeq;

    @Schema(description = "관리자 SEQ")
    @NotNull(message = "관리자 SEQ")
    private Long managerSeq;

    @Schema(description = "관리자 메뉴 SEQ")
    @NotNull(message = "관리자 메뉴 SEQ")
    private Long cmsMenuSeq;

    @Schema(description = "관리자 메뉴명")
    private String menuNm;

    @Schema(description = "관리자 메뉴 폴더")
    private String authDir;

    @Schema(description = "권한 (CRUD)")
    @NotNull(message = "권한 (CRUD)")
    private String authTypes;

}
