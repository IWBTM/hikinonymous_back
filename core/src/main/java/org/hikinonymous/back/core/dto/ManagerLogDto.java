package org.hikinonymous.back.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerLogDto extends CommonManagerDto {

    private String content;

    private String behaviorType;

}
