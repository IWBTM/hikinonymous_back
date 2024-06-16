package org.hikinonymous.back.cms.controller.adminMgmt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ManagerSimpleDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "ADMIN MANAGEMENT MENU", description = "ADMIN MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/admin/")
@RequiredArgsConstructor
public class AdminMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerService managerService;

    @Operation(
            summary = "관리자 리스트 조회",
            description = "관리자 리스트를 조회한다."
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

        Stream<ManagerEntity> managerEntities = managerService.streamAllBySuperYn("N");
        responseDto.setData(managerEntities.map(managerEntity -> {
            ManagerSimpleDto managerSimpleDto = (ManagerSimpleDto) CommonUtil.bindToObjectFromObjObject(managerEntity, ManagerSimpleDto.class);
            managerSimpleDto.setManagerStatus(managerEntity.getManagerStatus().getCodeNm());

            managerSimpleDto.setManagerId(EncUtil.decryptAES256(managerSimpleDto.getManagerId()));
            managerSimpleDto.setManagerNm(EncUtil.decryptAES256(managerSimpleDto.getManagerNm()));

            managerSimpleDto.setRegDate(CommonUtil.getDayByStrDate(managerSimpleDto.getRegDate()));
            managerSimpleDto.setLastLoginDate(CommonUtil.getDayByStrDate(managerSimpleDto.getLastLoginDate()));

            return managerSimpleDto;
        }).collect(Collectors.toList()));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "관리자 상세 조회",
            description = "관리자를 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "view")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "관리자 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        ManagerEntity managerEntity = managerService.findByManagerSeq(seq);
        if (Objects.isNull(managerEntity)) return ResponseUtil.canNotFoundManager(responseDto);
        ManagerDto managerDto = (ManagerDto) CommonUtil.bindToObjectFromObjObject(managerEntity, ManagerDto.class);
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
        if (Objects.isNull(managerDto)) return ResponseUtil.emptyRequestParameter(responseDto);

        CommonUtil.setClientIp(request, managerDto);
        managerService.proc(managerDto);
        return ResponseUtil.success(responseDto);
    }
}
