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
import org.hikinonymous.back.core.service.BoardService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "BOARD MANAGEMENT MENU", description = "BOARD MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/board/")
@RequiredArgsConstructor
public class BoardMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardService boardService;

    @Operation(
            summary = "게시글 리스트 조회",
            description = "게시글 리스트를 조회한다."
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
                    description = "게시글 타입"
            ) String boardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        Page<BoardEntity> boardEntityPages = boardService.findAllByBoardType(boardType, pageable);
        responseDto.setData(boardEntityPages.stream().map(boardEntity ->
            CommonUtil.bindToObjectFromObject(boardEntity, BoardSimpleDto.class)
        ));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "게시글 상세 조회",
            description = "게시글을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{boardType}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = "게시글 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        responseDto.setData(CommonUtil.bindToObjectFromObject(boardService.findById(seq), BoardDto.class));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "게시글 삭제 여부 수정",
            description = "게시글 삭제 여부를 수정한다."
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
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(boardDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setClientInfo(request, boardDto, manager);
        boardService.updateDelYn(boardDto);
        return ResponseUtil.success(responseDto);
    }

}
