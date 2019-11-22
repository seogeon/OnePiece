package com.hanaset.onepiecezoro.web.advice;

import com.hanaset.onepiecezoro.web.exception.ZoroException;
import com.hanaset.onepiecezoro.web.support.ZoroApiRestSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ZoroApiRestAdvice extends ZoroApiRestSupport {

    @ExceptionHandler(ZoroException.class)
    public ResponseEntity handleSkyResponseException(ZoroException ex) {
        return zoroException(ex.getCode(), ex.getMessage());
    }
}
