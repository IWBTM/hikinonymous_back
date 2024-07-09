package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.dto.CodeMasterDto;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.repository.code.CodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.Optional;

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

    public Page<CodeEntity> paging(Pageable pageable, String codeMaster) {
        return codeRepository.findByCodeMasterEntity(pageable, codeMasterService.findByCodeMaster(codeMaster));
    }

    public void proc(CodeDto codeDto) {
        CodeEntity codeEntity = Optional
                .ofNullable(codeDto.getCodeSeq())
                .flatMap(codeRepository::findById)
                .orElseGet(CodeEntity::new);
        codeEntity.setCodeMasterEntity(CodeMasterDto.bindToEntity(CodeMasterDto.builder().codeMaster(codeDto.getCodeMaster()).build()) );
        codeEntity.setCode(codeDto.getCode());
        codeEntity.setCodeNm(codeDto.getCodeNm());
        codeEntity.setSortOrder(codeDto.getSortOrder());
        codeEntity.setEtc(codeDto.getEtc());
        codeRepository.save(codeEntity);
    }
}
