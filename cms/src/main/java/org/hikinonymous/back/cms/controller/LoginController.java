package org.hikinonymous.back.cms.controller;

import jakarta.validation.Valid;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cms/login/")
public class LoginController {

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
