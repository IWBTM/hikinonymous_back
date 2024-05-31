package org.hikinonymous.back.core.handler;

import org.hikinonymous.back.core.dto.HttpResponseStatus;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
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
     * 요청 파라미터 중 필수 값 누락.
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
     * 요청 바디 누락.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.info("========== S HttpMessageNotReadableException ==========");
        logger.info("========== E HttpMessageNotReadableException ==========");
        return ResponseUtil.emptyRequestBody(new ResponseDto());
    }

}
