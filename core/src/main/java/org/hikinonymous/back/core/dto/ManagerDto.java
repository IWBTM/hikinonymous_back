package org.hikinonymous.back.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManagerDto implements Serializable {

    private Long managerSeq;

    private String managerId;

    private String managerNm;

    private String superYn;

    private String lastPwdDate;

    private String lastLoginDate;

    private Integer loginCnt;

    private String loginFailCnt;

    private String managerStatus;

    private String useYn;

    private String regDate;

}
