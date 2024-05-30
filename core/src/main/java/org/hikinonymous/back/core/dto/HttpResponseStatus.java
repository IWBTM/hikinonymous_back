package org.hikinonymous.back.core.dto;

import lombok.Getter;

@Getter
public enum HttpResponseStatus {
    
    SUCCESS(200, "성공"),
    BAD_REQUEST(400, "잘못된 접근"),
    SERVER_ERROR(500, "서버 에러");
    
    private final Integer code;

    private final String message;
    
    HttpResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
