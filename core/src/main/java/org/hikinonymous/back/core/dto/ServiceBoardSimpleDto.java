package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hikinonymous.back.core.entity.MemberEntity;

@Data
public class ServiceBoardSimpleDto extends CommonDto {

    private Long serviceBoardSeq;

    private String title;

    private String summary;

    private FileInfoEntity pcThumbImage;

    private FileInfoEntity moThumbImage;

    private String delYn;

    private String useYn;

    private MemberEntity register;

    private String regDate;

}
