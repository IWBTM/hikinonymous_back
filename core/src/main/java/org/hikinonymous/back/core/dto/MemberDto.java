package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CodeEntity;

@Data
public class MemberDto extends CommonDto {

    private Long memberSeq;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private CodeEntity memberStatus;

    private String gender;

    private Integer reportCnt;

    private String lastLoginDate;

    private CodeEntity joinType;

    private String privacyYn;

    private String receiveAdsEmailYn;

}
