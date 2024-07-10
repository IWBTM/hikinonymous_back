package org.hikinonymous.back.core.repository.serviceBoard;

import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceBoardRepository extends JpaRepository<ServiceBoardEntity, Long>, ServiceBoardRepositoryCustom {

    Page<ServiceBoardEntity> findAllByServiceBoardTypeAndDelYn(CodeEntity serviceBoardType, PageRequest of, String delYn);

}
