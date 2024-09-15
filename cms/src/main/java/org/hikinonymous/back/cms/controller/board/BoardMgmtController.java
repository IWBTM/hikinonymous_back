package org.hikinonymous.back.cms.controller.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.ReplyEntity;
import org.hikinonymous.back.core.service.BoardService;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.ReplyService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.hikinonymous.back.core.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "BOARD MANAGEMENT MENU", description = "BOARD MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/board/")
@RequiredArgsConstructor
public class BoardMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardService boardService;

    private final ReplyService replyService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "게시글";

    private final String SUB_MENU_NAME = "댓글";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{boardType}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable,
            @PathVariable(name = "boardType") @Parameter(
                    name = "boardType",
                    description = MENU_NAME + " 타입"
            ) String boardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        responseDto.setData(boardService.findAllByBoardType(boardType, pageable).map(boardSimpleDto -> {
            boardSimpleDto.setRegisterNm(EncUtil.decryptAES256(boardSimpleDto.getRegisterNm()));
            boardSimpleDto.setRegDate(CommonUtil.getDayByStrDate(boardSimpleDto.getRegDate()));
            return boardSimpleDto;
        }));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 상세 조회",
            description = MENU_NAME + "을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{boardType}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = MENU_NAME + " SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 상세", "R",  manager);

        responseDto.setData(BoardDto.bindToDtoForView(boardService.findById(seq)));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 삭제 여부 수정",
            description = MENU_NAME + " 삭제 여부를 수정한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{boardType}/updateDelYn")
    public ResponseDto updateDelYn(
            HttpServletRequest request,
            @RequestBody @Valid BoardDto boardDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 삭제 여부", "U",  manager);

        if (Objects.isNull(boardDto)) return ResponseUtil.emptyRequestBody(responseDto);

        boardService.updateDelYn(boardDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = SUB_MENU_NAME + " 리스트 조회",
            description = SUB_MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "reply/list/{boardSeq}")
    public ResponseDto replyList(
            HttpServletRequest request,
            @PageableDefault(sort = "replySeq", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable(name = "boardSeq") @Parameter(
                    name = "boardSeq",
                    description = MENU_NAME + " SEQ"
            ) Long boardSeq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, SUB_MENU_NAME + " 리스트", "R",  manager);

        Page<ReplyEntity> replyEntityPages = replyService.findAllByBoardSeq(boardSeq, pageable);
        responseDto.setData(replyEntityPages.map(replyEntity -> {
            ReplyDto replyDto = (ReplyDto) CommonUtil.bindToObjectFromObject(replyEntity, ReplyDto.class);
            replyDto.setRegisterNm(EncUtil.decryptAES256(replyEntity.getRegister().getMemberName()));
            replyDto.setRegDate(CommonUtil.getDayByStrDate(replyEntity.getRegDate()));
            return replyDto;
        }));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = SUB_MENU_NAME + " 삭제 여부 수정",
            description = SUB_MENU_NAME + " 삭제 여부를 수정한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "reply/updateDelYn")
    public ResponseDto updateDelYn(
            HttpServletRequest request,
            @RequestBody @Valid ReplyDto replyDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, SUB_MENU_NAME + " 삭제 여부", "U",  manager);

        if (Objects.isNull(replyDto)) return ResponseUtil.emptyRequestBody(responseDto);

        replyService.updateDelYn(replyDto);
        return ResponseUtil.success(responseDto);
    }
}
