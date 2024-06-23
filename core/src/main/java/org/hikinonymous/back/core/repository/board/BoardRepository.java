package org.hikinonymous.back.core.repository.board;

import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    Page<BoardEntity> findAllByBoardType(CodeEntity boardType, Pageable pageable);

}
