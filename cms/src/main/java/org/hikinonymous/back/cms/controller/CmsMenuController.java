package org.hikinonymous.back.cms.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/cms/menu/")
@RequiredArgsConstructor
public class CmsMenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CmsMenuService cmsMenuService;

    @GetMapping(value = "list")
    public ResponseDto list(
            HttpServletRequest request
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto managerDto = (ManagerDto) request.getAttribute("managerDto");

        if (managerDto.getSuperYn().equals("Y")) {
            List<CmsMenuDto> cmsMenuDtoList = new ArrayList<>();

            List<CmsMenuEntity> cmsMenuEntities = cmsMenuService.findAllByDisplayYnIsY("Y");
            for (CmsMenuEntity cmsMenuEntity: cmsMenuEntities) cmsMenuDtoList.add((CmsMenuDto) CommonUtil.bindToObjectFromObjObject(cmsMenuEntity, CmsMenuDto.class));

            responseDto.setData(cmsMenuDtoList);
            return ResponseUtil.success(responseDto);
        } else {
            return ResponseUtil.success(responseDto);
        }

    }
}
