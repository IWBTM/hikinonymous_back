package org.hikinonymous.back.core.exception;

public class BadRequestException extends BusinessException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public BadRequestException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
