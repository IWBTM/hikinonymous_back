package org.hikinonymous.back.cms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/cms/login/")
@RequiredArgsConstructor
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagerService managerService;

    @PostMapping(value = "proc")
    public ResponseDto proc(
            @RequestBody @Valid LoginDto loginDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        try {
            String encEmail = CommonUtil.encryptAES256(loginDto.getEmail());
            String encPwd = CommonUtil.encryptSHA256(loginDto.getPwd());
            ManagerEntity managerEntity = managerService.findByManagerId(encEmail);
            if (Objects.isNull(managerEntity)) return ResponseUtil.failedAuthentication(responseDto); // ID로 찾을 수 없음.
            responseDto.setData(managerEntity.getLoginFailCnt());
            if (managerEntity.getLoginFailCnt() > 5) return ResponseUtil.tooManyLoginFailedCnt(responseDto); // 로그인 실패 횟수 5회 이상
            if (managerEntity.getManagerPwd().equals(encPwd)) {
                managerService.updateSuccessLoginStatus(managerEntity);
                // JWT ?
                // 일반 문자열 난수 ?
                responseDto.setData("TOKEN");
                return ResponseUtil.success(responseDto); // 로그인 성공
            } else {
                managerService.updateFailLoginStatus(managerEntity);
                return ResponseUtil.failedAuthentication(responseDto); // PWD가 다름.
            }
        } catch (NoSuchElementException e) {
            return ResponseUtil.failedAuthentication(responseDto);
        } catch (ServerErrorException e) {
            return ResponseUtil.serverError(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.serverError(responseDto);
        }
    }
}
