package com.foodorderingapp.controller;

import com.foodorderingapp.exception.NotFoundException;
import com.foodorderingapp.exception.UnauthorizedExceptionHandler;
import com.foodorderingapp.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UnauthorizedExceptionHandler.class)
    public ResponseEntity<ExceptionResponse> unAthorizedException(final UnauthorizedExceptionHandler ex, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ex.getMessage());
        error.setCallerUrl(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(final NotFoundException ex, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ex.getMessage());
        error.setCallerUrl(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
    }

}