package org.hikinonymous.back.core.dto;

public enum HttpResponseStatus {
    
    SUCCESS(200, "성공"),
    SERVER_ERROR(500, "서버 에러");
    
    private Integer code;

    private String message;
    
    HttpResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
