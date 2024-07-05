package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "배너 리스트용 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerSimpleDto extends CommonDto {

    private Long bannerSeq;

    private String title;

    private CodeDto position;

    private String useYn;

}
