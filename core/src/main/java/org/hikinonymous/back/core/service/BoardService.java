package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.BoardDto;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.repository.board.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CodeService codeService;

    public List<BoardEntity> findAllByBoardType(String boardType) {
        return boardRepository.findAllByBoardType(codeService.findByCodeAndCodeMaster("BOARD_TYPE", boardType));
    }

    public BoardEntity findById(Long seq) {
        return boardRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Board Seq: " + seq + " not found")
        );
    }

    public void updateDelYn(BoardDto boardDto) {
        BoardEntity boardEntity = this.findById(boardDto.getBoardSeq());
        if (boardEntity.getDelYn().equals("Y")) boardEntity.setDelYn("N");
        else boardEntity.setDelYn("Y");

        boardRepository.save(boardEntity);
    }
}
