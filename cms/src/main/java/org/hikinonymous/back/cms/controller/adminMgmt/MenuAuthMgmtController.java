package org.hikinonymous.back.cms.controller.adminMgmt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.CmsMenuSimpleDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "ADMIN MENU AUTH MANAGEMENT", description = "ADMIN MENU AUTH MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/auth/")
@RequiredArgsConstructor
public class MenuAuthMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

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

        Stream<CmsMenuEntity> cmsMenuEntities = cmsMenuService.streamAllByDelYn("N");
        responseDto.setData(cmsMenuEntities.map(cmsMenuEntity ->
            (CmsMenuSimpleDto) CommonUtil.bindToObjectFromObjObject(cmsMenuEntity, CmsMenuSimpleDto.class)
        ).collect(Collectors.toList()));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "관리자 메뉴 상세 조회",
            description = "관리자 메뉴를 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "view")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "관리자 메뉴 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        CmsMenuEntity cmsMenuEntity = cmsMenuService.findByCmsMenuSeq(seq);
        if (Objects.isNull(cmsMenuEntity)) return ResponseUtil.canNotFoundManager(responseDto);
        responseDto.setData(CommonUtil.bindToObjectFromObjObject(cmsMenuEntity,  CmsMenuDto.class));
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
            @RequestBody @Valid CmsMenuDto cmsMenuDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(cmsMenuDto)) return ResponseUtil.emptyRequestParameter(responseDto);

        CommonUtil.setClientInfo(request, cmsMenuDto, manager);
        cmsMenuService.proc(cmsMenuDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "관리자 메뉴 순서 저장",
            description = "관리자 메뉴 순서를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "updateOrderSort")
    public ResponseDto updateOrderSort(
            HttpServletRequest request,
            @RequestBody @Valid List<CmsMenuDto> cmsMenuDtoList
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(cmsMenuDtoList) || cmsMenuDtoList.isEmpty()) return ResponseUtil.emptyRequestParameter(responseDto);

        cmsMenuService.updateOrderSort(request, cmsMenuDtoList, manager);
        return ResponseUtil.success(responseDto);
    }
}