package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.hikinonymous.back.core.repository.code.CodeRepository;
import org.hikinonymous.back.core.repository.codeMaster.CodeMasterRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    private final CodeMasterRepository codeMasterRepository;

    public CodeEntity findByCodeAndCodeMaster(String code, String codeMaster) {
        return codeRepository.findByCodeAndCodeMasterEntity(code, this.findCodeMasterByCodeMaster(codeMaster)).orElseThrow(() ->
                new NoSuchElementException("Code: " + code + " not found")
        );
    }

    public CodeMasterEntity findCodeMasterByCodeMaster(String code) {
        return codeMasterRepository.findByCodeMaster(code).orElseThrow(() ->
                new NoSuchElementException("CodeMaster: " + code + " not found")
        );
    }

}
