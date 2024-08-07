package org.hikinonymous.back.core.repository.codeMaster;

import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeMasterRepository extends JpaRepository<CodeMasterEntity, String>, CodeMasterRepositoryCustom {

    Optional<CodeMasterEntity> findByCodeMaster(String codeMaster);

    Page<CodeMasterEntity> findAllByDelYn(Pageable pageable, String delYn);

}
