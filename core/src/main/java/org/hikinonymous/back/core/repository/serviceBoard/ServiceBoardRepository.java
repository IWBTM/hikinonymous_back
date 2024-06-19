package org.hikinonymous.back.core.repository.serviceBoard;

import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBoardRepository extends JpaRepository<ServiceBoardEntity, Long>, ServiceBoardRepositoryCustom {

    List<ServiceBoardEntity> findAllByServiceBoardType(CodeEntity serviceBoardType);

}
