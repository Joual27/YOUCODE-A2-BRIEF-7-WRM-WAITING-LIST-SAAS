package org.youcode.WRM_V1.utils;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.youcode.WRM_V1.core.exceptions.EntityNotFoundException;
import org.youcode.WRM_V1.core.exceptions.InvalidArrivalTimeException;
import org.youcode.WRM_V1.core.exceptions.MissingDataException;
import org.youcode.WRM_V1.core.exceptions.NegativeCapacityException;
import org.youcode.WRM_V1.utils.DTOs.ErrorDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidationsException(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                err -> validationErrors.put(err.getField(), err.getDefaultMessage())
        );
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", validationErrors, LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGlobalExceptions(Exception e) {
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getAllValidationResults().forEach(validationResult -> {
            validationResult.getResolvableErrors().forEach(error -> {
                String message = error.getDefaultMessage();
                String field;
                if (error instanceof FieldError) {
                    field = ((FieldError) error).getField();
                } else if (error instanceof ObjectError) {
                    field = ((ObjectError) error).getObjectName();
                } else {
                    field = "Id";
                }
                validationErrors.put(field, message);
            });
        });

        if (validationErrors.containsKey("id")) {
            validationErrors.put("id", "No record was found with given ID !");
        }

        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", validationErrors, LocalDateTime.now());
    }


    @ExceptionHandler(NegativeCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleNegativeCapacityHandler(NegativeCapacityException e){
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(InvalidArrivalTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleInvalidArrivalTime(InvalidArrivalTimeException e){
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MissingDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMissingDataException(MissingDataException e){
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
    }

}
