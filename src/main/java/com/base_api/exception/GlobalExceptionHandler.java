package com.base_api.exception;

import com.base_api.model.common.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseDTO.ofError("An unexpected error occurred: " + e.getMessage()));
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ResponseDTO<String>> handleInvalidLoginException(InvalidLoginException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.ofError(e.getMessage()));
    }

}