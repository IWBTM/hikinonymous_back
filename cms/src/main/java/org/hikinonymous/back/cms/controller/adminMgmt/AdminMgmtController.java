package org.hikinonymous.back.cms.controller.adminMgmt;

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
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ManagerDto managerDto = (ManagerDto) request.getAttribute("managerDto");

        if (managerDto.getSuperYn().equals("Y")) {
            Stream<ManagerEntity> managerEntities = managerService.streamAllBySuperYn("N");
            responseDto.setData(managerEntities.map(managerEntity -> {
                managerEntity.setManagerId(EncUtil.decryptAES256(managerEntity.getManagerId()));
                managerEntity.setManagerNm(EncUtil.decryptAES256(managerEntity.getManagerNm()));
                managerEntity.setManagerHp(EncUtil.decryptAES256(managerEntity.getManagerHp()));
                return (ManagerDto) CommonUtil.bindToObjectFromObjObject(managerEntity, ManagerDto.class);
            }).collect(Collectors.toList()));
            return ResponseUtil.success(responseDto);
        }
        return ResponseUtil.success(responseDto);
    }
}
