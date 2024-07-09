package org.hikinonymous.back.core.service;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeMasterDto;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.hikinonymous.back.core.repository.codeMaster.CodeMasterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeMasterService {

    private final CodeMasterRepository codeMasterRepository;

    public CodeMasterEntity findByCodeMaster(String codeMaster) {
        return codeMasterRepository.findByCodeMaster(codeMaster).orElseThrow(() ->
                new ServerErrorException("CodeMaster: " + codeMaster + " not found", null)
        );
    }

    public Page<CodeMasterEntity> paging(Pageable pageable) {
        return codeMasterRepository.findAll(pageable);
    }

    public void proc(CodeMasterDto codeMasterDto) {
        CodeMasterEntity codeMasterEntity = Optional
                .ofNullable(codeMasterDto.getCodeMaster())
                .flatMap(codeMasterRepository::findById)
                .orElseGet(CodeMasterEntity::new);
        if (StringUtil.isNullOrEmpty(codeMasterEntity.getCodeMaster())) {
            codeMasterEntity = CodeMasterDto.bindToEntity(codeMasterDto);
        } else {
            codeMasterEntity.setCodeMasterNm(codeMasterDto.getCodeMasterNm());
            codeMasterEntity.setEtc(codeMasterDto.getEtc());
        }
        codeMasterRepository.save(codeMasterEntity);
    }
}
