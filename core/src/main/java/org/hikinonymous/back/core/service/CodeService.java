package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.repository.code.CodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    private final CodeMasterService codeMasterService;

    public CodeEntity findByCodeAndCodeMaster(String code, String codeMaster) {
        return codeRepository.findByCodeAndCodeMasterEntity(code, codeMasterService.findByCodeMaster(codeMaster)).orElseThrow(() ->
                new ServerErrorException("Code: " + code + " not found", null)
        );
    }

    public CodeEntity findByCodeSeq(Long codeSeq) {
        return codeRepository.findByCodeSeq(codeSeq).orElseThrow(() ->
                new ServerErrorException("Code Seq: " + codeSeq + " not found", null)
        );
    }

    public List<CodeEntity> findByCodeMaster(String codeMaster) {
        return codeRepository.findByCodeMasterEntity(codeMasterService.findByCodeMaster(codeMaster));
    }
}
