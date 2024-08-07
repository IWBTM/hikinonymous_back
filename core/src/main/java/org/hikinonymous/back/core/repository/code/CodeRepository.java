package org.hikinonymous.back.core.repository.code;

import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<CodeEntity, Long>, CodeRepositoryCustom {

    Optional<CodeEntity> findByCodeAndCodeMasterEntity(String code, CodeMasterEntity codeMasterEntity);

    Optional<CodeEntity> findByCodeSeq(Long codeSeq);

    List<CodeEntity> findByCodeMasterEntity(CodeMasterEntity codeMaster);

    Page<CodeEntity> findByCodeMasterEntity(Pageable pageable, CodeMasterEntity codeMaster);

    Page<CodeEntity> findByCodeMasterEntityAndDelYn(Pageable pageable, CodeMasterEntity byCodeMaster, String delYn);
}
