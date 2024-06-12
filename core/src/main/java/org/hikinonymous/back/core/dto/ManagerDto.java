package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.CodeEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class ManagerDto implements Serializable {

    private Long managerSeq;

    private String managerId;

    private String managerNm;

    private List<CmsMenuEntity> cmsMenuEntities;

    private String superYn;

    private String lastPwdDate;

    private String lastLoginDate;

    private String loginFailCnt;

}
