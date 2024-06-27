package org.hikinonymous.back.cms.controller.adminMgmt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.ManagerLogEntity;
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

@Tag(name = "MANAGER LOG MENU", description = "MANAGER LOG API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/admin/log/")
@RequiredArgsConstructor
public class ManagerLogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "관리자 로그";

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
        ManagerSimpleDto manager = (ManagerSimpleDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        Page<ManagerLogEntity> logPages = managerLogService.pagingByRegister(pageable, manager.getManagerSeq());

        Page<ManagerLogDto> logDtoPages = logPages.map(logEntity -> {
            ManagerLogDto logDto = (ManagerLogDto) CommonUtil.bindToObjectFromObject(logEntity, ManagerLogDto.class);
            logDto.setRegDate(CommonUtil.getDayWithMinuitByStrDate(logDto.getRegDate()));
            return logDto;
        });

        responseDto.setData(logDtoPages);
        return ResponseUtil.success(responseDto);
    }

}
