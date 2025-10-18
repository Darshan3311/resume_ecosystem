package com.zidio_task.resume.resume_ecosystem.exception;

import com.zidio_task.resume.resume_ecosystem.dto.DTOs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse(ex.getMessage(), "RESOURCE_NOT_FOUND");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleValidationException(ValidationException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse(ex.getMessage(), "VALIDATION_ERROR");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse(ex.getMessage(), "UNAUTHORIZED");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse(ex.getMessage(), "AUTHENTICATION_FAILED");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse("Invalid email or password", "BAD_CREDENTIALS");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DTOs.ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        DTOs.ErrorResponse error = new DTOs.ErrorResponse("Access denied", "ACCESS_DENIED");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("success", false);
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTOs.ErrorResponse> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        DTOs.ErrorResponse error = new DTOs.ErrorResponse(
            "An unexpected error occurred",
            "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
