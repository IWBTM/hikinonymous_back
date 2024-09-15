package org.hikinonymous.back.cms.controller.admin;

import ch.qos.logback.core.util.StringUtil;
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
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.hikinonymous.back.core.utils.SecurityUtil;
import org.mariadb.jdbc.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "ADMIN MENU MANAGEMENT", description = "ADMIN MENU MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/menu/")
@RequiredArgsConstructor
public class CmsMenuMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "관리자 메뉴";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "list/{menuLevel}")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault(sort = "sortOrder", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(
                    name = "menuLevel",
                    required = false
            ) @Parameter(
                    name = "menuLevel",
                    description = "관리자 메뉴 레벨"
            ) Integer menuLevel,
            @RequestParam(
                    name = "authDir",
                    required = false
            ) @Parameter(
                    name = "authDir",
                    description = "관리자 메뉴 AUTH DIR\n 하위 메뉴 조회 시 상위 메뉴의 AUTH DIR REQUIRED"
            ) String authDir
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        Page<CmsMenuEntity> cmsMenuEntityPages;
        if (Objects.isNull(menuLevel)) {
            cmsMenuEntityPages = cmsMenuService.paging(pageable);
        } else {
            if (StringUtil.isNullOrEmpty(authDir)) {
                cmsMenuEntityPages = cmsMenuService.pagingByMenuLevel(pageable, menuLevel);
            } else {
                cmsMenuEntityPages = cmsMenuService.pagingByMenuLevelAndAuthDir(pageable, menuLevel, authDir);
            }
        }
        responseDto.setData(cmsMenuEntityPages.map(cmsMenuEntity ->
                CommonUtil.bindToObjectFromObject(cmsMenuEntity, CmsMenuDto.class)
        ));
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
                    description = "관리자 메뉴 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 상세", "R",  manager);

        CmsMenuEntity cmsMenuEntity = cmsMenuService.findByCmsMenuSeq(seq);
        if (Objects.isNull(cmsMenuEntity)) return ResponseUtil.canNotFoundManager(responseDto);
        responseDto.setData(CommonUtil.bindToObjectFromObject(cmsMenuEntity,  CmsMenuDto.class));
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
            @RequestBody @Valid CmsMenuDto cmsMenuDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(cmsMenuDto.getCmsMenuSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        if (Objects.isNull(cmsMenuDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setManagerInfo(request, cmsMenuDto, manager);
        cmsMenuService.proc(cmsMenuDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 순서 저장",
            description = MENU_NAME + " 순서를 저장한다."
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
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + "정렬 순서", "U",  manager);

        if (Objects.isNull(cmsMenuDtoList) || cmsMenuDtoList.isEmpty()) return ResponseUtil.emptyRequestBody(responseDto);

        cmsMenuService.updateOrderSort(request, cmsMenuDtoList, manager);
        return ResponseUtil.success(responseDto);
    }
}
