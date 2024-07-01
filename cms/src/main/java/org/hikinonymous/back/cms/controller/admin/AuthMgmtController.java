package org.hikinonymous.back.cms.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.service.ManagerAuthService;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "ADMIN MENU AUTH MANAGEMENT", description = "ADMIN MENU AUTH MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/auth/")
@RequiredArgsConstructor
public class AuthMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerAuthService managerAuthService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "관리자 메뉴 권한";

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

        Page<ManagerAuthEntity> managerAuthEntityPages = managerAuthService.findAll(pageable);
        Page<ManagerAuthDto> managerAuthDtoPages = managerAuthEntityPages.map(managerAuthEntity ->
            (ManagerAuthDto) CommonUtil.bindToObjectFromObject(managerAuthEntity, ManagerAuthDto.class)
        );
        responseDto.setData(managerAuthEntityPages);;
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 저장",
            description = MENU_NAME + " 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @RequestBody @Valid ManagerAuthDto managerAuthDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(managerAuthDto.getManagerAuthSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        if (Objects.isNull(managerAuthDto)) return ResponseUtil.emptyRequestBody(responseDto);
        CommonUtil.setClientInfo(request, managerAuthDto, manager);
        managerAuthService.proc(managerAuthDto);
        return ResponseUtil.success(responseDto);
    }

}
