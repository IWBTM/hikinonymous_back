package org.hikinonymous.back.core.repository.codeMaster;

import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeMasterRepository extends JpaRepository<CodeMasterEntity, Long>, CodeMasterRepositoryCustom {

    Optional<CodeMasterEntity> findByCodeMaster(String codeMaster);

}
