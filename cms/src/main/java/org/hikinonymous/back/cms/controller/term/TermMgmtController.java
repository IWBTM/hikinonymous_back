package org.hikinonymous.back.cms.controller.term;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.service.CodeMasterService;
import org.hikinonymous.back.core.service.CodeService;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.ServiceBoardService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "TERM MANAGEMENT", description = "TERM MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/term/")
@RequiredArgsConstructor
public class TermMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ServiceBoardService serviceBoardService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "이용 약관 ";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{serviceBoardType}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable,
            @PathVariable(
                    name = "serviceBoardType"
            ) @Parameter(
                    name = "serviceBoardType",
                    description = "term"
            ) String serviceBoardType
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        Page<ServiceBoardEntity> serviceBoardEntityPages = serviceBoardService.findAllByServiceBoardType(serviceBoardType, pageable);
        responseDto.setData(serviceBoardEntityPages.map(boardEntity -> {
                ServiceBoardDto serviceBoardDto = (ServiceBoardDto) CommonUtil.bindToObjectFromObject(boardEntity, ServiceBoardDto.class);

                serviceBoardDto.setRegDate(CommonUtil.getDayByStrDate(serviceBoardDto.getRegDate()));
                serviceBoardDto.setRegisterNm(EncUtil.decryptAES256(serviceBoardDto.getRegisterNm()));

                serviceBoardDto.setUpdDate(CommonUtil.getDayByStrDate(serviceBoardDto.getUpdDate()));
                serviceBoardDto.setUpdaterNm(EncUtil.decryptAES256(serviceBoardDto.getUpdaterNm()));
                return serviceBoardDto;
        }));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 저장",
            description = MENU_NAME + " 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{serviceBoardType}/proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @PathVariable(
                    name = "serviceBoardType"
            ) @Parameter(
                    name = "serviceBoardType",
                    description = "term"
            ) String serviceBoardType,
            @RequestBody(required = false) @Valid ServiceBoardDto serviceBoardDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(serviceBoardDto.getServiceBoardSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        CommonUtil.setManagerInfo(request, serviceBoardDto, manager);
        serviceBoardDto.setServiceBoardType(CodeDto.builder().code(serviceBoardType.toUpperCase()).build());
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
    @PostMapping(value = "{serviceBoardType}/updateDelYn")
    public ResponseDto updateDelYn(
            HttpServletRequest request,
            @PathVariable(name = "serviceBoardType") @Parameter(
                    name = "serviceBoardType",
                    description = MENU_NAME + " 타입"
            ) String serviceBoardType,
            @RequestBody @Valid ServiceBoardDelYnDto serviceBoardDelYnDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + " " + serviceBoardType + " 삭제 여부", "U",  manager);

        CommonUtil.setManagerInfo(request, serviceBoardDelYnDto, manager);
        serviceBoardService.updateDelYn(serviceBoardDelYnDto);
        return ResponseUtil.success(responseDto);
    }
}
