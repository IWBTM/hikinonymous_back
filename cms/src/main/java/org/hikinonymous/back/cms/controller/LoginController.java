package org.hikinonymous.back.cms.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/cms/login/")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "proc")
    public ResponseDto proc(
            @RequestBody @Valid LoginDto loginDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        try {
            return ResponseUtil.success(responseDto);
        } catch (Exception e) {
            return ResponseUtil.serverError(responseDto);
        }
    }
}
