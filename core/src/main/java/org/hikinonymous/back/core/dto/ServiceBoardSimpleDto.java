package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.MemberEntity;

@Data
public class ServiceBoardSimpleDto extends CommonDto {

    private Long serviceBoardSeq;

    private String title;

    private String summary;

    private String delYn;

    private String useYn;

    private MemberEntity register;

    private String regDate;

}
