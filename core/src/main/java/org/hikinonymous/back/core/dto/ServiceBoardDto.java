package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.MemberEntity;

@Data
public class ServiceBoardDto extends CommonDto {

    private Long serviceBoardSeq;

    private String title;

    private String summary;

    private String content;

    private String delYn;

    private String useYn;

}
