package org.hikinonymous.back.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.JwtUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "CMS LOGIN", description = "CMS LOGIN API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/login/")
@RequiredArgsConstructor
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerService managerService;

    @Operation(
            summary = "관리자 로그인",
            description = "관리자의 정보로 Authorization 값을 응답 받는다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "proc")
    public ResponseDto proc(
            @RequestBody @Valid LoginDto loginDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        logger.info("========== TRY LOGIN EMAIL:: {} ==========", loginDto.getEmail());
        String encEmail = EncUtil.encryptAES256(loginDto.getEmail());
        String encPwd = EncUtil.encryptSHA256(loginDto.getPwd());
        ManagerEntity managerEntity = managerService.findByManagerId(encEmail);
        if (Objects.isNull(managerEntity)) return ResponseUtil.canNotFoundManager(responseDto);
        responseDto.setData(managerEntity.getLoginFailCnt());
        if (managerEntity.getLoginFailCnt() > 5) return ResponseUtil.tooManyLoginFailedCnt(responseDto);
        if (managerEntity.getManagerPwd().equals(encPwd)) {
            managerService.updateSuccessLoginStatus(managerEntity);
            responseDto.setData(JwtUtil.makeJwt(managerEntity.getManagerSeq()));
            return ResponseUtil.success(responseDto);
        } else {
            managerService.updateFailLoginStatus(managerEntity);
            responseDto.setData(managerEntity.getLoginFailCnt());
            return ResponseUtil.canNotFoundManager(responseDto);
        }
    }
}
