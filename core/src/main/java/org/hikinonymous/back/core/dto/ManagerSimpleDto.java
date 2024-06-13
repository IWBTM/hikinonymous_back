package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CmsMenuEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class ManagerSimpleDto implements Serializable {

    private Long managerSeq;

    private String managerId;

    private String managerNm;

    private String lastLoginDate;

    private String managerStatus;

    private String useYn;

    private String regDate;

}
