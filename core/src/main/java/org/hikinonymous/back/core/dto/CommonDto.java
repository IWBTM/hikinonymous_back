package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.ManagerEntity;

import java.io.Serializable;

@Schema(
        description = "공통 DTO"
)
@Data
public class CommonDto implements Serializable {

    private ManagerEntity register;

    private String registerNm;

    private String registerIp;

    private String regDate;

    private ManagerEntity updater;

    private String updaterNm;

    private String updaterIp;

    private String updDate;

}
