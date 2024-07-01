package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.hikinonymous.back.core.repository.codeMaster.CodeMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

@Service
@RequiredArgsConstructor
public class CodeMasterService {

    private final CodeMasterRepository codeMasterRepository;

    public CodeMasterEntity findByCodeMaster(String codeMaster) {
        return codeMasterRepository.findByCodeMaster(codeMaster).orElseThrow(() ->
                new ServerErrorException("CodeMaster: " + codeMaster + " not found", null)
        );
    }

}
