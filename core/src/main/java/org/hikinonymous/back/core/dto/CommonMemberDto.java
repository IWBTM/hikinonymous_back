package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.MemberEntity;

import java.io.Serializable;

@Schema(
        description = "공통 DTO"
)
@Data
public class CommonMemberDto implements Serializable {

    @Schema(hidden = true)
    private MemberEntity register;

    @Schema(hidden = true)
    private String registerNm;

    @Schema(hidden = true)
    private String registerIp;

    @Schema(hidden = true)
    private String regDate;

    @Schema(hidden = true)
    private MemberEntity updater;

    @Schema(hidden = true)
    private String updaterNm;

    @Schema(hidden = true)
    private String updaterIp;

    @Schema(hidden = true)
    private String updDate;

}
