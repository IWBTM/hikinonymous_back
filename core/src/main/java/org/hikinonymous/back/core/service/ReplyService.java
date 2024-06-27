package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ReplyDto;
import org.hikinonymous.back.core.entity.ReplyEntity;
import org.hikinonymous.back.core.repository.reply.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final BoardService boardService;

    private final CodeService codeService;

    public Page<ReplyEntity> findAllByBoardSeq(Long boardSeq, Pageable pageable) {
        return replyRepository.findAllByBoardEntity(boardService.findById(boardSeq), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public ReplyEntity findById(Long seq) {
        return replyRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Reply Seq: " + seq + " not found")
        );
    }

    public void updateDelYn(ReplyDto replyDto) {
        ReplyEntity replyEntity = this.findById(replyDto.getReplySeq());
        if (replyEntity.getDelYn().equals("Y")) replyEntity.setDelYn("N");
        else replyEntity.setDelYn("Y");

        replyRepository.save(replyEntity);
    }
}
