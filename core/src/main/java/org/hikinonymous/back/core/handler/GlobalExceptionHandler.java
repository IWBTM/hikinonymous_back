package org.hikinonymous.back.core.handler;

import org.hikinonymous.back.core.dto.HttpResponseStatus;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.out.println("====================");
        System.out.println("e.getCause():: " + e.getCause());
        System.out.println("e.getMessage():: " + e.getMessage());
        System.out.println("====================");
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("========== S MethodArgumentNotValidException ==========");
        ResponseDto responseDto = new ResponseDto();
        ResponseUtil.validEmailForm(responseDto);
        String message = e.getMessage();
        String messageTemp;
        messageTemp = message.substring(message.lastIndexOf("[") + 1);
        messageTemp = messageTemp.substring(0, messageTemp.indexOf("]"));
        if (message.contains("NotBlank")) messageTemp += "(은)는 필수값입니다.";
        responseDto.setMessage(messageTemp);
        System.out.println("========== E MethodArgumentNotValidException ==========");
        return responseDto;
    }

}
