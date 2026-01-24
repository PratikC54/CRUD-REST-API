package com.CRUD.demo.Exceptions;

import com.CRUD.demo.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(
            UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    ex.getMessage()
                ));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException hmns ) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                new ApiError(
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        hmns.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationError(MethodArgumentNotValidException mex) {
        String error = mex.getBindingResult().getFieldErrors()
                .stream().map(err -> err.getField() + ": "+ err.getDefaultMessage())
                .collect(Collectors.joining(","));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(400, error));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiError> handleDuplicateEmailException(DuplicateEmailException dex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(409, dex.getMessage()));
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ApiError> handleDuplicateEmailException(DuplicateUserException duex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(409, duex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericExceptions (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(500, "Internal server error"));
    }


}