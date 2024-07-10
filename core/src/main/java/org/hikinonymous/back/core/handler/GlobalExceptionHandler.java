package org.hikinonymous.back.core.handler;

import com.querydsl.core.types.ExpressionException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.UnexpectedTypeException;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity handleException(Exception e) {
        logger.info("====================");
        logger.info("e.getCause():: " + e.getCause());
        logger.info("e.getMessage():: " + e.getMessage());
        e.printStackTrace();
        logger.info("====================");
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 올바르지 않은 AccessToken.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseDto handleAuthenticationException(AuthenticationException e) {
        logger.info("========== S AuthenticationException ==========");
        e.printStackTrace();
        logger.info("========== E AuthenticationException ==========");
        return ResponseUtil.failedAuthentication(new ResponseDto());
    }

    /**
     * DB 조회 시 데이터를 찾을 수 없음.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseDto handleNoSuchElementException(NoSuchElementException e) {
        logger.info("========== S NoSuchElementException ==========");
        logger.info("========== E NoSuchElementException ==========");
        return ResponseUtil.failedAuthentication(new ResponseDto());
    }

    /**
     * 요청 파라미터 중 필수 값 누락. 1
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.info("========== S MethodArgumentNotValidException ==========");
        ResponseDto responseDto = new ResponseDto();
        String message = e.getMessage();
        String messageTemp;
        messageTemp = message.substring(message.lastIndexOf("[") + 1);
        messageTemp = messageTemp.substring(0, messageTemp.indexOf("]"));
        
        if (message.contains("NotBlank")) {
            ResponseUtil.validRequestParameterForm(responseDto);
            responseDto.setMessage(messageTemp + "(은)는 필수값입니다.");
        } else {
            ResponseUtil.emptyRequestParameter(responseDto);
            responseDto.setMessage(messageTemp);
        }
        
        logger.info("========== E MethodArgumentNotValidException ==========");
        return responseDto;
    }


    /**
     * 어노테이션 내 타입 에러 ?
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseDto handleUnexpectedTypeException(UnexpectedTypeException e) {
        logger.info("========== S UnexpectedTypeException ==========");
        logger.info("========== E UnexpectedTypeException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }


    /**
     * 요청 바디 누락.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.info("========== S HttpMessageNotReadableException ==========");
        logger.info("========== E HttpMessageNotReadableException ==========");
        return ResponseUtil.emptyRequestBody(new ResponseDto());
    }

    /**
     * 리소스를 찾을 수 없음.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseDto handleNoResourceFoundException(NoResourceFoundException e) {
        logger.info("========== S NoResourceFoundException ==========");
        e.printStackTrace();
        logger.info("========== E NoResourceFoundException ==========");
        return ResponseUtil.badRequest(new ResponseDto());
    }

    /**
     * 요청 컨텐츠 타입 안 맞음.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.info("========== S HttpMediaTypeNotSupportedException ==========");
        logger.info("========== E HttpMediaTypeNotSupportedException ==========");
        return ResponseUtil.httpMediaTypeNotSupported(new ResponseDto());
    }

    /**
     * 요청 컨텐츠 타입 안 맞음.
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseDto handleHttpMessageConversionException(HttpMessageConversionException e) {
        logger.info("========== S HttpMessageConversionException ==========");
        e.printStackTrace();
        logger.info("========== E HttpMessageConversionException ==========");
        return ResponseUtil.httpMediaTypeNotSupported(new ResponseDto());
    }

    /**
     * DB 에러.
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseDto handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        logger.info("========== S SQLIntegrityConstraintViolationException ==========");
        logger.info("========== E SQLIntegrityConstraintViolationException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * 코드 안 맞음.
     */
    @ExceptionHandler(ServerErrorException.class)
    public ResponseDto handleServerErrorException(ServerErrorException e) {
        logger.info("========== S ServerErrorException ==========");
        logger.error("========== REASON:: " + e.getMessage() + " ==========");
        logger.info("========== E ServerErrorException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * NullPointerException.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseDto handleNullPointerException(NullPointerException e) {
        logger.info("========== S NullPointerException ==========");
        logger.error("========== REASON:: " + e.getMessage() + " ==========");
        logger.info("========== E NullPointerException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * JpaSystemException.
     */
    @ExceptionHandler(JpaSystemException.class)
    public ResponseDto handleJpaSystemException(JpaSystemException e) {
        logger.info("========== S JpaSystemException ==========");
        logger.error("========== REASON:: " + e.getMessage() + " ==========");
        logger.info("========== E JpaSystemException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto handleIllegalArgumentException(IllegalArgumentException e) {
        logger.info("========== S IllegalArgumentException ==========");
        logger.error("========== REASON:: " + e.getMessage() + " ==========");
        logger.info("========== E IllegalArgumentException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * ExpressionException.
     */
    @ExceptionHandler(ExpressionException.class)
    public ResponseDto handleExpressionException(ExpressionException e) {
        logger.info("========== S ExpressionException ==========");
        logger.error("========== REASON:: " + e.getMessage() + " ==========");
        logger.info("========== E ExpressionException ==========");
        return ResponseUtil.serverError(new ResponseDto());
    }

    /**
     * ExpiredJwtException.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseDto handleExpiredJwtException(ExpiredJwtException e) {
        logger.info("========== S ExpiredJwtException ==========");
        logger.info("========== E ExpiredJwtException ==========");
        return ResponseUtil.failedAuthentication(new ResponseDto());
    }

}
