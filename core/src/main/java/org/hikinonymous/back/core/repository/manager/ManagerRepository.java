package org.hikinonymous.back.core.repository.manager;

import org.hikinonymous.back.core.entity.ManagerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>, ManagerRepositoryCustom {

    ManagerEntity findByManagerIdAndManagerPwd(String managerId, String managerPwd);

    ManagerEntity findByManagerId(String managerId);

    Optional<ManagerEntity> findByManagerSeq(long seq);

    Page<ManagerEntity> findAllByDelYn(PageRequest of, String delYn);

    ManagerEntity findByManagerIdAndDelYn(String email, String delYn);

}
