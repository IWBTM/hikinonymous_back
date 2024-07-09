package org.hikinonymous.back.core.dto;

import lombok.Data;
import org.hikinonymous.back.core.entity.CodeEntity;

@Data
public class CodeDto {

    private Long codeSeq;

    private String codeMaster;

    private String code;

    private String codeNm;

    private String etc;

    private Integer sortOrder;

    public static CodeDto bindToDto(CodeEntity codeEntity) {
        CodeDto codeDto = new CodeDto();
        codeDto.setCodeSeq(codeEntity.getCodeSeq());
        codeDto.setCodeMaster(codeEntity.getCodeMasterEntity().getCodeMaster());
        codeDto.setCode(codeEntity.getCode());
        codeDto.setCodeNm(codeEntity.getCodeNm());
        codeDto.setEtc(codeEntity.getEtc());
        codeDto.setSortOrder(codeEntity.getSortOrder());
        return codeDto;
    }

}
