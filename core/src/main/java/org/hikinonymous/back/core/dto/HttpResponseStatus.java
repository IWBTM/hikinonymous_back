package org.hikinonymous.back.core.dto;

import lombok.Getter;

@Getter
public enum HttpResponseStatus {
    
    SUCCESS(200, "성공 했습니다."),

    FAILED_AUTHENTICATION(300, "올바르지 않은 토큰입니다."),
    TOO_MANY_LOGIN_FAILED_CNT(301, "로그인 실패 횟수가 5회 이상입니다. \n관리자에게 문의해주세요."),
    CAN_NOT_FOUND_USER(302, "유저 정보를 찾을 수 없습니다."),

    BAD_REQUEST(400, "잘못된 접근입니다."),
    INVALID_REQUEST_PARAMETER_FORM(401, "요청 파라미터 값의 형식이 올바르지 않습니다."),
    EMPTY_REQUEST_PARAMETER(402, "필수 값인 요청 파라미터가 비어 있습니다."),
    EMPTY_REQUEST_BODY(403, "요청 바디가 비어 있습니다."),

    SERVER_ERROR(500, "서버에서 에러가 발생했습니다.");
    
    private final Integer code;

    private final String message;
    
    HttpResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
