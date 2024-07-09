package org.hikinonymous.back.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.CodeMasterEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeMasterDto {

    private String codeMaster;

    private String codeMasterNm;

    private String etc;

    public static CodeMasterDto bindToDto(CodeMasterEntity codeMasterEntity) {
        CodeMasterDto codeDto = new CodeMasterDto();
        codeDto.setCodeMaster(codeMasterEntity.getCodeMaster());
        codeDto.setCodeMasterNm(codeMasterEntity.getCodeMasterNm());
        codeDto.setEtc(codeMasterEntity.getEtc());
        return codeDto;
    }

    public static CodeMasterEntity bindToEntity(CodeMasterDto codeMasterDto) {
        CodeMasterEntity codeMasterEntity = new CodeMasterEntity();
        codeMasterEntity.setCodeMaster(codeMasterDto.getCodeMaster());
        codeMasterEntity.setCodeMasterNm(codeMasterDto.getCodeMasterNm());
        codeMasterEntity.setEtc(codeMasterDto.getEtc());
        return codeMasterEntity;
    }

}
