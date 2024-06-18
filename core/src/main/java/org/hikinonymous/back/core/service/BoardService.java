package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.repository.board.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CodeService codeService;

    public List<BoardEntity> findAllByBoardType(String boardType) {
        return boardRepository.findAllByBoardType(codeService.findByCodeAndCodeMaster("BOARD_TYPE", boardType));
    }
}
