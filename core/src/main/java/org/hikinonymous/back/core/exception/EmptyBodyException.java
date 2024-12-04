package org.hikinonymous.back.core.exception;

public class EmptyBodyException extends BusinessException {

    private static final String MESSAGE = "필수값이 누락되었습니다.";

    public EmptyBodyException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
