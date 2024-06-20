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
import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.service.BoardService;
import org.hikinonymous.back.core.service.ServiceBoardService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "SERVICE BOARD MANAGEMENT MENU", description = "SERVICE BOARD MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/service/")
@RequiredArgsConstructor
public class ServiceBoardMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ServiceBoardService serviceBoardService;

    @Operation(
            summary = "서비스 게시글 리스트 조회",
            description = "서비스 게시글 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{serviceBoardType}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "serviceBoardType",
                    description = "서비스 게시글 타입"
            ) String serviceBoardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        List<ServiceBoardEntity> serviceBoardEntities = serviceBoardService.findAllByServiceBoardType(serviceBoardType);
        responseDto.setData(serviceBoardEntities.stream().map(boardEntity ->
            CommonUtil.bindToObjectFromObjObject(boardEntity, ServiceBoardSimpleDto.class)
        ));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "서비스 게시글 상세 조회",
            description = "서비스 게시글을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{serviceBoardType}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "서비스 게시글 SEQ"
            ) Long seq,
            @PathVariable @Parameter(
                    name = "boardType",
                    description = "게시글 타입"
            ) String serviceBoardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        responseDto.setData(CommonUtil.bindToObjectFromObjObject(serviceBoardService.findById(seq), ServiceBoardDto.class));
        return ResponseUtil.success(responseDto);
    }

}
