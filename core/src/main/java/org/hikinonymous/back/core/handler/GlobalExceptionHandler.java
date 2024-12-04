package org.hikinonymous.back.core.handler;

import com.querydsl.core.types.ExpressionException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.UnexpectedTypeException;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.dto.response.ErrorResponse;
import org.hikinonymous.back.core.exception.BadRequestException;
import org.hikinonymous.back.core.exception.EmptyBodyException;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        logger.error("handleAllException", e);
        return ErrorResponse.builder()
                .code(500)
                .message("서버에서 오류가 발생했습니다.\n잠시 후 다시 시도해 주세요.")
                .build();
    }

    /**
     * 올바르지 않은 AccessToken.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException(AuthenticationException e) {
        logger.error("handleAuthenticationException", e);
        return ErrorResponse.builder()
                .code(300)
                .message("유효하지 않은 토큰입니다.")
                .build();
    }

    /**
     * ExpiredJwtException.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse handleExpiredJwtException(ExpiredJwtException e) {
        logger.error("handleExpiredJwtException", e);
        return ErrorResponse.builder()
                .code(300)
                .message("만료된 토큰입니다.")
                .build();
    }

    /**
     * DB 조회 시 데이터를 찾을 수 없음.
     */
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(BadRequestException e) {
        logger.error("handleBadRequestException", e);
        return ErrorResponse.builder()
                .code(404)
                .message(e.getMessage())
                .build();
    }

    /**
     * DB 조회 시 데이터를 찾을 수 없음.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        logger.error("handleNoSuchElementException", e);
        return ErrorResponse.builder()
                .code(404)
                .message(e.getMessage() + "을(를) 찾을 수 없습니다.")
                .build();
    }

    /**
     * 요청 파라미터 중 필수 값 누락.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("handleMethodArgumentNotValidException", e);
        String message = e.getMessage();
        String messageTemp;
        messageTemp = message.substring(message.lastIndexOf("[") + 1);
        messageTemp = messageTemp.substring(0, messageTemp.indexOf("]"));
        
        if (message.contains("NotBlank")) {
            return ErrorResponse.builder()
                    .code(404)
                    .message(messageTemp + "(은)는 필수값입니다.")
                    .build();
        } else {
//            필수 값인 요청 파라미터가 비어 있습니다.
            return ErrorResponse.builder()
                    .code(404)
                    .message(messageTemp)
                    .build();
        }
    }

    /**
     * 요청 바디 누락.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("handleHttpMessageNotReadableException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("필수값이 누락되었습니다.")
                .build();
    }

    /**
     * 요청 바디 누락.
     */
    @ExceptionHandler(EmptyBodyException.class)
    public ErrorResponse handleEmptyBodyException(EmptyBodyException e) {
        logger.error("handleEmptyBodyException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("필수값이 누락되었습니다.")
                .build();
    }

    /**
     * 리소스를 찾을 수 없음.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException e) {
        logger.error("handleNoResourceFoundException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("잘못된 접근입니다.")
                .build();
    }

    /**
     * 요청 컨텐츠 타입 안 맞음.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("handleHttpMediaTypeNotSupportedException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("잘못된 요청입니다.")
                .build();
    }

    /**
     * 요청 컨텐츠 타입 안 맞음.
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ErrorResponse handleHttpMessageConversionException(HttpMessageConversionException e) {
        logger.error("handleHttpMediaTypeNotSupportedException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("잘못된 요청입니다.")
                .build();
    }

    /**
     * InvalidDataAccessApiUsageException.
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ErrorResponse handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        logger.error("handleHttpMediaTypeNotSupportedException", e);
        return ErrorResponse.builder()
                .code(404)
                .message("필수값이 누락되었습니다.")
                .build();
    }

}
