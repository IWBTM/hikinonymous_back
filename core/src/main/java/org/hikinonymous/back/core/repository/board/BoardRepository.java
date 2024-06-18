package org.hikinonymous.back.core.repository.board;

import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    List<BoardEntity> findAllByBoardType(CodeEntity boardType);

}
