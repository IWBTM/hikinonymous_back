package org.hikinonymous.back.core.repository.board;

import org.hikinonymous.back.core.dto.BoardSimpleDto;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardSimpleDto> findAllByBoardType(CodeEntity codeEntity, Pageable pageable);
}
