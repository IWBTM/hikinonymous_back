package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.*;

@Data
public class BoardSimpleDto extends CommonManagerDto {

    private Long boardSeq;

    private String title;

    private CategoryEntity category;

    private String delYn;

}
