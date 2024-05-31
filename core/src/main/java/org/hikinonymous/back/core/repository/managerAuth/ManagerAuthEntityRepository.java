package org.hikinonymous.back.core.repository.managerAuth;

import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerAuthEntityRepository extends JpaRepository<ManagerAuthEntity, Long>, ManagerAuthRepositoryCustom {

    List<ManagerAuthEntity> findAllByManager(ManagerEntity managerEntity);

}
