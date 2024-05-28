package org.hikinonymous.back.core.utils;

import org.hikinonymous.back.core.dto.HttpResponseStatus;
import org.hikinonymous.back.core.dto.ResponseDto;

public class ResponseUtil {

    public static ResponseDto success(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.SUCCESS.getCode());
        responseDto.setMessage(HttpResponseStatus.SUCCESS.getMessage());
        return responseDto;
    }

    public static ResponseDto serverError(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.SERVER_ERROR.getCode());
        responseDto.setMessage(HttpResponseStatus.SERVER_ERROR.getMessage());
        return responseDto;
    }
}
