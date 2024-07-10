package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.CategoryEntity;

@Schema(
        description = "카테고리 DTO"
)
@Data
public class CategoryDto extends CommonManagerDto {

    private Long categorySeq;

    private String categoryName;

    private String etc;

    private Integer sortOrder;

    private Integer categoryLevel;

    private CategoryEntity topCategory;

    private String useYn;

    private String delYn;

}
