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
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
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

import java.util.List;
import java.util.Objects;

import static org.hikinonymous.back.core.utils.CommonUtil.*;

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
            summary = MENU_NAME + "을 메뉴 리스트와 함께 조회",
            description = MENU_NAME + "을 메뉴 리스트와 함께 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "menu/list/{managerSeq}")
    public ResponseDto menuList(
            HttpServletRequest request,
            @PathVariable(
                    name = "managerSeq"
            ) @Parameter(
                    name = "managerSeq",
                    description = "관리자 SEQ\n 관리자 SEQ로 조회 시 REQUIRED"
            ) Long managerSeq,
            @RequestParam(
                    name = "authDir",
                    required = false
            ) @Parameter(
                    name = "authDir",
                    description = "관리자 메뉴 AUTH DIR\n 하위 메뉴 조회 시 상위 메뉴의 AUTH DIR REQUIRED"
            ) String authDir,
            @RequestParam(
                    name = "isExist"
            ) @Parameter(
                    name = "isExist",
                    description = "권한이 있는 메뉴 조회일 경우 true"
            ) boolean isExist
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + "와 메뉴 리스트", "R",  manager);

        List<MenuAuthDto> menuAuthDtoList = managerAuthService.findAllByManagerSeqAndAuthDir(managerSeq, authDir, isExist);
        responseDto.setData(menuAuthDtoList);
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
            @RequestBody @Valid MenuAuthDto menuAuthDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(menuAuthDto.getManagerAuthSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        if (Objects.isNull(menuAuthDto)) return ResponseUtil.emptyRequestBody(responseDto);
        managerAuthService.proc(menuAuthDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 삭제",
            description = MENU_NAME + " 정보를 삭제한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "del")
    public ResponseDto del(
            HttpServletRequest request,
            @RequestBody @Valid MenuAuthDto menuAuthDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME, "D",  manager);

        if (Objects.isNull(menuAuthDto)) return ResponseUtil.emptyRequestBody(responseDto);
        managerAuthService.del(menuAuthDto);
        return ResponseUtil.success(responseDto);
    }

}
