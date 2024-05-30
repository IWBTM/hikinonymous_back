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
     * FAILED_AUTHENTICATION
     * CODE - 300
     */
    public static ResponseDto failedAuthentication(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.FAILED_AUTHENTICATION.getCode());
        responseDto.setMessage(HttpResponseStatus.FAILED_AUTHENTICATION.getMessage());
        return responseDto;
    }

    /**
     * VALID_REQUEST_PARAMETER_FORM
     * CODE - 401
     */
    public static ResponseDto validRequestParameterForm(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.VALID_REQUEST_PARAMETER_FORM.getCode());
        responseDto.setMessage(HttpResponseStatus.VALID_REQUEST_PARAMETER_FORM.getMessage());
        return responseDto;
    }

    /**
     * EMPTY_REQUEST_PARAMETER
     * CODE - 402
     */
    public static ResponseDto emptyRequestParameter(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.EMPTY_REQUEST_PARAMETER.getCode());
        responseDto.setMessage(HttpResponseStatus.EMPTY_REQUEST_PARAMETER.getMessage());
        return responseDto;
    }

    /**
     * EMPTY_REQUEST_BODY
     * CODE - 403
     */
    public static ResponseDto emptyRequestBody(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.EMPTY_REQUEST_BODY.getCode());
        responseDto.setMessage(HttpResponseStatus.EMPTY_REQUEST_BODY.getMessage());
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
