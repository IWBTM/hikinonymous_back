package org.hikinonymous.back.core.dto;

import lombok.Data;

import java.io.Serializable;

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
