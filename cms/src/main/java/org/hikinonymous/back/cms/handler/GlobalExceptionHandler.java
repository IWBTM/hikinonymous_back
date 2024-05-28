package org.hikinonymous.back.cms.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
