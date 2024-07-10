package org.hikinonymous.back.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardSimpleDto extends CommonMemberDto {

    private Long boardSeq;

    private String title;

    private CategoryDto category;

    private Long viewCnt;

    private Long replyCnt;

    private String delYn;

}
