package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;

@Data
public class TermDto extends CommonManagerDto {

    private Long serviceBoardSeq;
    
    private String title;
    
    private String content;
    
    private CodeEntity serviceBoardType;
    
    private String useYn;

    public static TermDto bindToDto(ServiceBoardEntity serviceBoardEntity) {
        TermDto termDto = new TermDto();
        return termDto;
    }

}
