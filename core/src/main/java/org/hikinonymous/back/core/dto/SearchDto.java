package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.ManagerEntity;

import java.io.Serializable;

@Schema(
        description = "검색 DTO"
)
@Data
public class SearchDto {

    private int pageNum;

    private int pageSize;

    private String searchCondition1;

    private String searchCondition2;

    private String searchCondition3;

    private String searchCondition4;

    private String searchCondition5;

}
