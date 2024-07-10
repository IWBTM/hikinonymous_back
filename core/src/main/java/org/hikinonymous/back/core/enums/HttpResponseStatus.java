package org.hikinonymous.back.core.enums;

import lombok.Getter;

@Getter
public enum HttpResponseStatus {
    
    SUCCESS(200, "성공  하였습니다."),

    FAILED_AUTHENTICATION(300, "올바르지 않은 토큰입니다."),
    TOO_MANY_LOGIN_FAILED_CNT(301, "로그인 실패 횟수가 5회 이상입니다. \n관리자에게 문의해주세요."),
    CAN_NOT_FOUND_MANAGER(302, "관리자 정보를 찾을 수 없습니다."),
    CAN_NOT_FOUND_MEMBER(303, "회원 정보를 찾을 수 없습니다."),
    CAN_NOT_USE_MANAGER(304, "사용이 제한된 관리자 계정입니다."),
    UN_ACTIVE_MANAGER(305, "해당 계정은 비활성화된 계정입니다."),

    BAD_REQUEST(400, "잘못된 접근입니다."),
    INVALID_REQUEST_PARAMETER_FORM(401, "요청 파라미터 값의 형식이 올바르지 않습니다."),
    EMPTY_REQUEST_PARAMETER(402, "필수 값인 요청 파라미터가 비어 있습니다."),
    EMPTY_REQUEST_BODY(403, "요청 바디가 비어 있습니다."),
    EMPTY_PROPERTY_IN_REQUEST_BODY(404, "요청 바디 내 데이터가 비어 있습니다."),

    HTTP_MEDIA_TYPE_NOT_SUPPORTED(490, "서버에서 요구하는 Content-Type이 아닙니다."),

    SERVER_ERROR(500, "서버에서 에러가 발생 하였습니다.");
    
    private final Integer code;

    private final String message;
    
    HttpResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
