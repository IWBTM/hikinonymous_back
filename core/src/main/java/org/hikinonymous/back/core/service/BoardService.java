package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.BoardDto;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.repository.board.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CodeService codeService;

    public Page<BoardEntity> findAllByBoardType(String boardType, Pageable pageable) {
        return boardRepository.findAllByBoardType(codeService.findByCodeAndCodeMaster(boardType, "BOARD_TYPE"), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
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
