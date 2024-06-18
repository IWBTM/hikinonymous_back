package org.hikinonymous.back.core.repository.manager;

import org.hikinonymous.back.core.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>, ManagerRepositoryCustom {

    ManagerEntity findByManagerIdAndManagerPwd(String managerId, String managerPwd);

    ManagerEntity findByManagerId(String managerId);

    Optional<ManagerEntity> findByManagerSeq(long seq);

    Stream<ManagerEntity> streamAllBySuperYn(String superYn);

}
