package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "배너 삭제용 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDelYnDto extends CommonManagerDto {

    private Long bannerSeq;

    private String delYn;

}
