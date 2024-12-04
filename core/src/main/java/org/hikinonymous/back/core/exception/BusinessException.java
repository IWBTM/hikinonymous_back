package org.hikinonymous.back.core.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

}
