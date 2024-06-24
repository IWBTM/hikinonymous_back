package org.hikinonymous.back.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagerLogDto extends CommonDto {

    private String content;

    private String behaviorType;

}
