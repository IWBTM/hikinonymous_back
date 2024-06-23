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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            @PageableDefault Pageable pageable,
            @PathVariable(name = "serviceBoardType") @Parameter(
                    name = "serviceBoardType",
                    description = "서비스 게시글 타입"
            ) String serviceBoardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        Page<ServiceBoardEntity> serviceBoardEntityPages = serviceBoardService.findAllByServiceBoardType(serviceBoardType, pageable);
        responseDto.setData(serviceBoardEntityPages.stream().map(boardEntity ->
            CommonUtil.bindToObjectFromObject(boardEntity, ServiceBoardSimpleDto.class)
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
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = "서비스 게시글 SEQ"
            ) Long seq,
            @PathVariable(name = "serviceBoardType") @Parameter(
                    name = "boardType",
                    description = "게시글 타입"
            ) String serviceBoardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        responseDto.setData(CommonUtil.bindToObjectFromObject(serviceBoardService.findById(seq), ServiceBoardDto.class));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "서비스 게시글 저장",
            description = "서비스 게시글을 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{serviceBoardType}/proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @RequestBody @Valid ServiceBoardDto serviceBoardDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(serviceBoardDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setClientInfo(request, serviceBoardDto, manager);
        serviceBoardService.proc(serviceBoardDto);
        return ResponseUtil.success(responseDto);
    }

}
