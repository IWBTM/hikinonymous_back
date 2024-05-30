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

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.info("====================");
        logger.info("e.getCause():: " + e.getCause());
        logger.info("e.getMessage():: " + e.getMessage());
        logger.info("====================");
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException e) {
        logger.info("====================");
        logger.info("e.getCause():: " + e.getCause());
        logger.info("e.getMessage():: " + e.getMessage());
        logger.info("====================");
        return new ResponseEntity(HttpStatus.OK);
    }

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.info("========== S HttpMessageNotReadableException ==========");
        logger.info("========== E HttpMessageNotReadableException ==========");
        return ResponseUtil.emptyRequestBody(new ResponseDto());
    }

}
