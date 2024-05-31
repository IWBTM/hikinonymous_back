package org.hikinonymous.back.core.dto;

import lombok.Data;

@Data
public class CmsMenuDto {

    private Long cmsMenuSeq;

    private String menuCode;

    private String menuNm;

    private Long menuLevel;

    private String authDir;

    private String filePath;

    private Long sortOrder;

}
