package org.hikinonymous.back.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonDto implements Serializable {

    private String registerIp;

    private String regDate;

    private String updaterIp;

    private String updDate;

}
