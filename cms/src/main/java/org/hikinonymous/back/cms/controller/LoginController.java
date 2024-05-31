package org.hikinonymous.back.cms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.JwtUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import java.util.HashMap;
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
            logger.info("========== TRY LOGIN EMAIL:: " + loginDto.getEmail() + " ==========");
            String encEmail = EncUtil.encryptAES256(loginDto.getEmail());
            String encPwd = EncUtil.encryptSHA256(loginDto.getPwd());
            ManagerEntity managerEntity = managerService.findByManagerId(encEmail);
            if (Objects.isNull(managerEntity)) return ResponseUtil.failedAuthentication(responseDto); // ID로 찾을 수 없음.
            responseDto.setData(managerEntity.getLoginFailCnt());
            if (managerEntity.getLoginFailCnt() > 5) return ResponseUtil.tooManyLoginFailedCnt(responseDto); // 로그인 실패 횟수 5회 이상
            if (managerEntity.getManagerPwd().equals(encPwd)) {
                managerService.updateSuccessLoginStatus(managerEntity);
                responseDto.setData(JwtUtil.makeJwt(managerEntity.getManagerSeq()));
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
