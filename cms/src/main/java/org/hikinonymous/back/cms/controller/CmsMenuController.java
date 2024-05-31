package org.hikinonymous.back.cms.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.service.ManagerAuthService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping(value = "/cms/menu/")
@RequiredArgsConstructor
public class CmsMenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

    private final ManagerAuthService managerAuthService;

    @GetMapping(value = "list")
    public ResponseDto list(
            HttpServletRequest request
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto managerDto = (ManagerDto) request.getAttribute("managerDto");

        if (managerDto.getSuperYn().equals("Y")) {
            Stream<CmsMenuEntity> cmsMenuEntities = cmsMenuService.streamAllByDisplayYn("Y");
            responseDto.setData(cmsMenuEntities.map(userEntity -> (CmsMenuDto) CommonUtil.bindToObjectFromObjObject(userEntity, CmsMenuDto.class)).collect(Collectors.toList()));
            return ResponseUtil.success(responseDto);
        } else {
            Stream<CmsMenuEntity> cmsMenuEntities = cmsMenuService.streamAllByManagerSeq(managerDto.getManagerSeq());
            return ResponseUtil.success(responseDto);
        }

    }
}
