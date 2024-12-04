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
     * EMPTY_REQUEST_BODY
     * CODE - 403
     */
    public static ResponseDto emptyRequestBody(ResponseDto responseDto) {
        responseDto.setCode(HttpResponseStatus.EMPTY_REQUEST_BODY.getCode());
        responseDto.setMessage(HttpResponseStatus.EMPTY_REQUEST_BODY.getMessage());
        return responseDto;
    }

}
