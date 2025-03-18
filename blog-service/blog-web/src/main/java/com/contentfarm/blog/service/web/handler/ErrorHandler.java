package com.contentfarm.blog.service.web.handler;

import com.contentfarm.dto.common.ErrorResponse;
import com.contentfarm.exception.NotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandler {
    @ResponseBody
    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundByIdException exception) {
        return new ResponseEntity<>(ErrorResponse.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
