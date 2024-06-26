package org.hikinonymous.back.cms.controller.adminMgmt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.ManagerService;
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

@Tag(name = "MANAGER MANAGEMENT MENU", description = "MANAGER MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/manager/")
@RequiredArgsConstructor
public class ManagerMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerService managerService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "관리자";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        Page<ManagerEntity> managerPages = managerService.paging(pageable);

        Page<ManagerSimpleDto> managerSimpleDtoPages = managerPages.map(managerEntity -> {
            ManagerSimpleDto managerSimpleDto = (ManagerSimpleDto) CommonUtil.bindToObjectFromObject(managerEntity, ManagerSimpleDto.class);
            managerSimpleDto.setManagerStatus(managerEntity.getManagerStatus().getCodeNm());

            managerSimpleDto.setManagerId(EncUtil.decryptAES256(managerSimpleDto.getManagerId()));
            managerSimpleDto.setManagerNm(EncUtil.decryptAES256(managerSimpleDto.getManagerNm()));

            managerSimpleDto.setRegDate(CommonUtil.getDayByStrDate(managerSimpleDto.getRegDate()));
            managerSimpleDto.setLastLoginDate(CommonUtil.getDayByStrDate(managerSimpleDto.getLastLoginDate()));

            return managerSimpleDto;
        });

        responseDto.setData(managerSimpleDtoPages);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 상세 조회",
            description = MENU_NAME + "를 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = MENU_NAME + " SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 상세", "R",  manager);

        ManagerEntity managerEntity = managerService.findByManagerSeq(seq);
        if (Objects.isNull(managerEntity)) return ResponseUtil.canNotFoundManager(responseDto);
        ManagerDto managerDto = (ManagerDto) CommonUtil.bindToObjectFromObject(managerEntity, ManagerDto.class);
        managerDto.setManagerStatus(managerEntity.getManagerStatus().getCodeNm());

        managerDto.setManagerId(EncUtil.decryptAES256(managerDto.getManagerId()));
        managerDto.setManagerNm(EncUtil.decryptAES256(managerDto.getManagerNm()));

        managerDto.setRegDate(CommonUtil.getDayByStrDate(managerDto.getRegDate()));
        managerDto.setLastLoginDate(CommonUtil.getDayByStrDate(managerDto.getLastLoginDate()));

        responseDto.setData(managerDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "관리자 저장",
            description = "관리자 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @RequestBody @Valid ManagerDto managerDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(managerDto)) return ResponseUtil.emptyRequestBody(responseDto);

        String behaviorType;
        if (Objects.isNull(managerDto.getManagerSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        CommonUtil.setClientInfo(request, managerDto, manager);
        managerService.proc(managerDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 비밀번호 저장",
            description = MENU_NAME + " 비밀번호를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "updatePwd")
    public ResponseDto updatePwd(
            HttpServletRequest request,
            @RequestBody @Valid ManagerPwdDto managerDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(managerDto)) return ResponseUtil.emptyRequestBody(responseDto);

        managerLogService.proc(request, MENU_NAME + " 비밀번호", "U",  manager);

        CommonUtil.setClientInfo(request, managerDto, manager);
        managerService.updatePwd(managerDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 삭제 여부 수정",
            description = MENU_NAME + " 삭제 여부를 수정한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "updateDelYn")
    public ResponseDto updateDelYn(
            HttpServletRequest request,
            @RequestBody @Valid ManagerDelYnDto managerDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(managerDto)) return ResponseUtil.emptyRequestBody(responseDto);

        managerLogService.proc(request, MENU_NAME + " 삭제 여부", "U",  manager);

        CommonUtil.setClientInfo(request, managerDto, manager);
        managerService.updateDelYn(managerDto);
        return ResponseUtil.success(responseDto);
    }
}
