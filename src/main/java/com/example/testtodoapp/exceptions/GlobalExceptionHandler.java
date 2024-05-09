package com.example.testtodoapp.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            DataIntegrityViolationException.class,
            MethodArgumentTypeMismatchException.class,
            MissingPathVariableException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
    })
    public Map<String, String> handleInvalidArgument(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
        }
        if (exception instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) exception;
            errorMap.put(ex.getName(), "Ошибка типа переменной пути. Переменная' " + ex.getValue() + " не является типом " + ex.getRequiredType());
        }
        if (exception instanceof MissingPathVariableException) {
            MissingPathVariableException ex = (MissingPathVariableException) exception;
            errorMap.put(ex.getVariableName(), "Missing path variable '" + ex.getParameter() + "'");
        }
        if (exception instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException ex = (HttpMessageNotReadableException) exception;
            errorMap.put("Ошибка при создании задачи: ", ex.getCause().getMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({UserExistException.class})
    public Map<String, String> handleUserExistException(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class, UserNotFoundException.class})
    public Map<String, String> handleResourceException(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
