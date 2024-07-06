package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CodeEntity;

@Data
public class CodeDto {

    private Long codeSeq;

    private String code;

    private String codeNm;

    public static CodeDto bindToDto(CodeEntity codeEntity) {
        CodeDto codeDto = new CodeDto();
        codeDto.setCodeSeq(codeEntity.getCodeSeq());
        codeDto.setCode(codeEntity.getCode());
        codeDto.setCodeNm(codeEntity.getCodeNm());
        return codeDto;
    }

}
