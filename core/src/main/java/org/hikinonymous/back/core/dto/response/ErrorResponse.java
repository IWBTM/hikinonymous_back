package org.hikinonymous.back.core.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Integer code;
    private final String message;

    @Builder
    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
