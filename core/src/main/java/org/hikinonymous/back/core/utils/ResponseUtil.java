package org.hikinonymous.back.core.utils;

import org.hikinonymous.back.core.enums.HttpResponseStatus;
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
     * TOO_MANY_LOGIN_FAILED_CNT
     * CODE - 301
     */
    public static ResponseDto tooManyLoginFailedCnt(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.TOO_MANY_LOGIN_FAILED_CNT.getCode());
        responseDto.setMessage(HttpResponseStatus.TOO_MANY_LOGIN_FAILED_CNT.getMessage());
        return responseDto;
    }

    /**
     * CAN_NOT_FOUND_MANAGER
     * CODE - 302
     */
    public static ResponseDto canNotFoundManager(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.CAN_NOT_FOUND_MANAGER.getCode());
        responseDto.setMessage(HttpResponseStatus.CAN_NOT_FOUND_MANAGER.getMessage());
        return responseDto;
    }

    /**
     * CAN_NOT_FOUND_MEMBER
     * CODE - 303
     */
    public static ResponseDto canNotFoundMember(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.CAN_NOT_FOUND_MEMBER.getCode());
        responseDto.setMessage(HttpResponseStatus.CAN_NOT_FOUND_MEMBER.getMessage());
        return responseDto;
    }

    /**
     * CAN_NOT_USE_MANAGER
     * CODE - 304
     */
    public static ResponseDto canNotUseManager(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.CAN_NOT_USE_MANAGER.getCode());
        responseDto.setMessage(HttpResponseStatus.CAN_NOT_USE_MANAGER.getMessage());
        return responseDto;
    }

    /**
     * UN_ACTIVE_MANAGER
     * CODE - 305
     */
    public static ResponseDto unActiveManager(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.UN_ACTIVE_MANAGER.getCode());
        responseDto.setMessage(HttpResponseStatus.UN_ACTIVE_MANAGER.getMessage());
        return responseDto;
    }

    /**
     * BAD_REQUEST
     * CODE - 400
     */
    public static ResponseDto badRequest(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.BAD_REQUEST.getCode());
        responseDto.setMessage(HttpResponseStatus.BAD_REQUEST.getMessage());
        return responseDto;
    }

    /**
     * INVALID_REQUEST_PARAMETER_FORM
     * CODE - 401
     */
    public static ResponseDto inValidRequestParameterForm(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.INVALID_REQUEST_PARAMETER_FORM.getCode());
        responseDto.setMessage(HttpResponseStatus.INVALID_REQUEST_PARAMETER_FORM.getMessage());
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
     * EMPTY_PROPERTY_IN_REQUEST_BODY
     * CODE - 404
     */
    public static ResponseDto emptyPropertyInRequestBody(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.EMPTY_PROPERTY_IN_REQUEST_BODY.getCode());
        responseDto.setMessage(HttpResponseStatus.EMPTY_PROPERTY_IN_REQUEST_BODY.getMessage());
        return responseDto;
    }

    /**
     * HTTP_MEDIA_TYPE_NOT_SUPPORTED
     * CODE - 490
     */
    public static ResponseDto httpMediaTypeNotSupported(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getCode());
        responseDto.setMessage(HttpResponseStatus.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getMessage());
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
