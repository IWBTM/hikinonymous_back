package org.hikinonymous.back.core.dto;

import lombok.Data;

@Data
public class MemberSimpleDto {

    private Long memberSeq;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private CodeDto memberStatus;

    private Integer reportCnt;

    private Long totalBoardCnt;

    private Long totalReplyCnt;

    private String lastLoginDate;

    private String regDate;

}
