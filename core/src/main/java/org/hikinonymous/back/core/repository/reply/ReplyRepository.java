package org.hikinonymous.back.core.repository.reply;

import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>, ReplyRepositoryCustom {

    Page<ReplyEntity> findAllByBoardEntity(BoardEntity boardEntity, Pageable pageable);

}
