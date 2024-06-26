package org.hikinonymous.back.core.repository.managerLog;

import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.entity.ManagerLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerLogRepository extends JpaRepository<ManagerLogEntity, Long>, ManagerLogRepositoryCustom {

    Page<ManagerLogEntity> findAllByRegister(Pageable pageable, ManagerEntity register);

}
