package org.hikinonymous.back.cms.controller.banner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.service.BannerService;
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

@Tag(name = "BANNER AUTH MANAGEMENT", description = "BANNER AUTH MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/site/banner/")
@RequiredArgsConstructor
public class BannerMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BannerService bannerService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "배너";

    @Operation(
            summary = MENU_NAME + " 리스트를 조회",
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

        Page<BannerEntity> bannerEntityPages = bannerService.paging(pageable);
        responseDto.setData(bannerEntityPages.map(bannerEntity ->
            CommonUtil.bindToObjectFromObject(bannerEntity, BannerSimpleDto.class)
        ));
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
            @ModelAttribute @Valid BannerDto bannerDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(bannerDto.getBannerSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        CommonUtil.setClientInfo(request, bannerDto, manager);
        bannerService.proc(bannerDto);
        return ResponseUtil.success(responseDto);
    }
}
