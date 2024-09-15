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
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.ServiceBoardService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.hikinonymous.back.core.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Tag(name = "SERVICE BOARD MANAGEMENT MENU", description = "SERVICE BOARD MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/service/")
@RequiredArgsConstructor
public class ServiceBoardMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ServiceBoardService serviceBoardService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "서비스 게시글";

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
        managerLogService.proc(request, MENU_NAME + " " + boardType + " 리스트", "R",  manager);

        Page<ServiceBoardEntity> serviceBoardEntityPages = serviceBoardService.findAllByServiceBoardType(boardType, pageable);
        responseDto.setData(serviceBoardEntityPages.map(boardEntity -> {
            ServiceBoardSimpleDto serviceBoardSimpleDto = (ServiceBoardSimpleDto) CommonUtil.bindToObjectFromObject(boardEntity, ServiceBoardSimpleDto.class);
            serviceBoardSimpleDto.setRegDate(CommonUtil.getDayByStrDate(serviceBoardSimpleDto.getRegDate()));
            serviceBoardSimpleDto.setRegisterNm(EncUtil.decryptAES256(serviceBoardSimpleDto.getRegisterNm()));
            return serviceBoardSimpleDto;
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
            ) Long seq,
            @PathVariable(name = "boardType") @Parameter(
                    name = "boardType",
                    description = MENU_NAME + " 타입"
            ) String boardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " " + boardType + " 상세", "R",  manager);

        responseDto.setData(ServiceBoardDto.bindToDtoForView(serviceBoardService.findById(seq)));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 저장",
            description = MENU_NAME + "을 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{boardType}/proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @PathVariable(name = "boardType") @Parameter(
                    name = "boardType",
                    description = MENU_NAME + " 타입"
            ) String boardType,
            @ModelAttribute @Valid ServiceBoardDto serviceBoardDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(serviceBoardDto.getServiceBoardSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " " + boardType + " 정보", behaviorType,  manager);

        CommonUtil.setManagerInfo(request, serviceBoardDto, manager);
        serviceBoardDto.setServiceBoardType(CodeDto.builder().code(boardType.toUpperCase()).build());
        serviceBoardService.proc(serviceBoardDto);
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
            @PathVariable(name = "boardType") @Parameter(
                    name = "boardType",
                    description = MENU_NAME + " 타입"
            ) String boardType,
            @RequestBody @Valid ServiceBoardDelYnDto serviceBoardDelYnDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + " " + boardType + " 삭제 여부", "U",  manager);

        CommonUtil.setManagerInfo(request, serviceBoardDelYnDto, manager);
        serviceBoardService.updateDelYn(serviceBoardDelYnDto);
        return ResponseUtil.success(responseDto);
    }
}
