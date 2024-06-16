package org.hikinonymous.back.cms.controller.cmsMenu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "CMS MENU", description = "CMS MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/menu/")
@RequiredArgsConstructor
public class CmsMenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

    @Operation(summary = "관리자 메뉴 리스트 조회", description = "관리자의 권한을 토대로 관리자 메뉴 리스트를 조회한다.")
    @ApiResponse(description = "응답 에러 코드 DOC 참고")
    @GetMapping(value = "list")
    public ResponseDto list(HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (manager.getSuperYn().equals("Y")) {
            Stream<CmsMenuEntity> cmsMenuEntities = cmsMenuService.streamAllByDisplayYn("Y");
            responseDto.setData(cmsMenuEntities.map(userEntity -> (CmsMenuDto) CommonUtil.bindToObjectFromObjObject(userEntity, CmsMenuDto.class)).collect(Collectors.toList()));
            return ResponseUtil.success(responseDto);
        } else {
            responseDto.setData(cmsMenuService.findAllByManagerSeq(manager.getManagerSeq()));
            return ResponseUtil.success(responseDto);
        }

    }
}
