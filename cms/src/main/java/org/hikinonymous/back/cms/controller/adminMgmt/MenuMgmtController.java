package org.hikinonymous.back.cms.controller.adminMgmt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.CmsMenuSimpleDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "ADMIN MENU MANAGEMENT", description = "ADMIN MENU MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/menu/")
@RequiredArgsConstructor
public class MenuMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

    @Operation(
            summary = "관리자 메뉴 리스트 조회",
            description = "관리자 메뉴 리스트를 조회한다."
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

        Stream<CmsMenuEntity> cmsMenuEntities = cmsMenuService.streamAllByDelYn("N");
        responseDto.setData(cmsMenuEntities.map(cmsMenuEntity ->
            (CmsMenuSimpleDto) CommonUtil.bindToObjectFromObjObject(cmsMenuEntity, CmsMenuSimpleDto.class)
        ).collect(Collectors.toList()));
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
            @PathVariable Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

//        ManagerEntity managerEntity = cmsMenuService.findByManagerSeq(seq);
//        if (Objects.isNull(managerEntity)) return ResponseUtil.canNotFoundManager(responseDto);
//        ManagerDto managerDto = (ManagerDto) CommonUtil.bindToObjectFromObjObject(managerEntity, ManagerDto.class);
//        managerDto.setManagerStatus(managerEntity.getManagerStatus().getCodeNm());
//
//        managerDto.setManagerId(EncUtil.decryptAES256(managerDto.getManagerId()));
//        managerDto.setManagerNm(EncUtil.decryptAES256(managerDto.getManagerNm()));
//
//        managerDto.setRegDate(CommonUtil.getDayByStrDate(managerDto.getRegDate()));
//        managerDto.setLastLoginDate(CommonUtil.getDayByStrDate(managerDto.getLastLoginDate()));
//
//        responseDto.setData(managerDto);
        return ResponseUtil.success(responseDto);
    }
}
