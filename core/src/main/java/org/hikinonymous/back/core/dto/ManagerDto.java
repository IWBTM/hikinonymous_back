package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CodeEntity;

import java.io.Serializable;

@Data
public class ManagerDto implements Serializable {

    private Long managerSeq;

    private String managerId;

    private String managerNm;

    private CodeEntity menuAuth;

    private String superYn;

    private String lastPwdDate;

}
