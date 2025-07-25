package com.service.payment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientMoneyException.class)
    public ResponseEntity<ErrorResponse<String>> handleInsufficientMoneyException(InsufficientMoneyException insufficientMoneyException) {
        return ResponseEntity.badRequest().body(new ErrorResponse<>(UUID.randomUUID(), insufficientMoneyException.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse<>(UUID.randomUUID(), notFoundException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, List<String>>>> handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .toList();
        return new ResponseEntity<>(new ErrorResponse<>(UUID.randomUUID(), getErrorsMap(errors)), BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
