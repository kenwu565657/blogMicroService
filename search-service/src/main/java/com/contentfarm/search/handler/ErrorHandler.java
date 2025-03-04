package com.contentfarm.search.handler;

import com.contentfarm.dto.common.ErrorResponse;
import com.contentfarm.search.exception.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(DocumentNotFoundException exception) {
        return new ResponseEntity<>(ErrorResponse.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
