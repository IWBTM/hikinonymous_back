package org.hikinonymous.back.core.dto;

import lombok.Data;

@Data
public class MemberSimpleDto extends CommonDto {

    private Long memberSeq;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private CodeDto memberStatus;

    private String gender;

    private Integer reportCnt;

    private String lastLoginDate;

}
