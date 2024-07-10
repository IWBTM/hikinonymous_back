package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "문의 삭제 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryDelYnDto extends CommonMemberDto {

    @Schema(description = "문의 SEQ")
    private Long inquirySeq;

    @Schema(description = "삭제 여부")
    private String delYn;

}
