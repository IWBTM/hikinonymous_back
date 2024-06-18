package org.hikinonymous.back.cms.controller.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.service.BoardService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @PathVariable @Parameter(
                    name = "boardType",
                    description = "게시글 타입"
            ) String boardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        List<BoardEntity> boardEntities = boardService.findAllByBoardType(boardType);
        responseDto.setData(boardEntities.stream().map(boardEntity ->
            (BoardSimpleDto) CommonUtil.bindToObjectFromObjObject(boardEntity, BoardSimpleDto.class)
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
    @GetMapping(value = "{boardType}/view")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "관리자 SEQ"
            ) Long seq,
            @PathVariable @Parameter(
                    name = "boardType",
                    description = "게시글 타입"
            ) String boardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        return ResponseUtil.success(responseDto);
    }

}
