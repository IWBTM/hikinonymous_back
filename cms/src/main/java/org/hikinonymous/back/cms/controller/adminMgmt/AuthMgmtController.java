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
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.service.ManagerAuthService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Tag(name = "ADMIN MENU AUTH MANAGEMENT", description = "ADMIN MENU AUTH MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/auth/")
@RequiredArgsConstructor
public class AuthMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerAuthService managerAuthService;

    @Operation(
            summary = "관리자 메뉴 권한 리스트 조회",
            description = "관리자 메뉴 권한 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "list")
    public ResponseDto list(
            HttpServletRequest request
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        List<ManagerAuthEntity> managerAuthEntities = managerAuthService.findAll();
        responseDto.setData(managerAuthEntities.stream().map(managerAuthEntity ->
            (ManagerAuthDto) CommonUtil.bindToObjectFromObjObject(managerAuthEntity, ManagerAuthDto.class)
        ).collect(Collectors.toList()));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "관리자 메뉴 저장",
            description = "관리자 메뉴 정보를 저장한다."
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
        if (Objects.isNull(managerAuthDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setClientInfo(request, managerAuthDto, manager);
        managerAuthService.proc(managerAuthDto);
        return ResponseUtil.success(responseDto);
    }

}
