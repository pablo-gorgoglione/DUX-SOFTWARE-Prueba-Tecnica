package com.base_api.exception;

import com.base_api.model.common.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        String errorMessage = e.getLocalizedMessage();

        if (e instanceof ResponseStatusException responseStatusException) {
            errorMessage = responseStatusException.getReason();
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidLoginException(InvalidLoginException e) {
        logger.error("Invalid login attempt: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}