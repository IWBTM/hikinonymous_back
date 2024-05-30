package org.hikinonymous.back.core.dto;

import lombok.Getter;

@Getter
public enum HttpResponseStatus {
    
    SUCCESS(200, "성공 했습니다."),
    BAD_REQUEST(400, "잘못된 접근입니다."),
    VALID_REQUEST_PARAMETER_FORM(401, "요청 파라미터 값의 형식이 올바르지 않습니다."),
    EMPTY_REQUEST_PARAMETER(402, "요청 파라미터 필수 값입니다."),
    EMPTY_REQUEST_BODY(403, "요청 바디가 비어 있습니다."),
    SERVER_ERROR(500, "서버에서 에러가 발생했습니다.");
    
    private final Integer code;

    private final String message;
    
    HttpResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
