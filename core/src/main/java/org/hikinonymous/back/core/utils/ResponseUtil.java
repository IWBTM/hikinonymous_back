package org.hikinonymous.back.core.utils;

import org.hikinonymous.back.core.dto.HttpResponseStatus;
import org.hikinonymous.back.core.dto.ResponseDto;

public class ResponseUtil {

    /**
     * SUCCESS
     * CODE - 200
     */
    public static ResponseDto success(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.SUCCESS.getCode());
        responseDto.setMessage(HttpResponseStatus.SUCCESS.getMessage());
        return responseDto;
    }

    /**
     * VALID_EMAIL_FORM
     * CODE - 401
     */
    public static ResponseDto validEmailForm(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.VALID_EMAIL_FORM.getCode());
        responseDto.setMessage(HttpResponseStatus.VALID_EMAIL_FORM.getMessage());
        return responseDto;
    }

    /**
     * SERVER_ERROR
     * CODE - 500
     */
    public static ResponseDto serverError(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.SERVER_ERROR.getCode());
        responseDto.setMessage(HttpResponseStatus.SERVER_ERROR.getMessage());
        return responseDto;
    }
}
