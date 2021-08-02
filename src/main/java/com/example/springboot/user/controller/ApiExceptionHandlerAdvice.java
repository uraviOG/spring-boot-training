package com.example.springboot.user.controller;

import com.example.springboot.user.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO : uncomment to see how to create custom exception handlers
@ControllerAdvice
@Order(-1) // order tells spring the priority to run this handler, Lower values have higher priority
public class ApiExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidError(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ErrorResponse(errors.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleValidationError(ValidationException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundError(UserNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidFormatError(InvalidFormatException ex) {
        return new ErrorResponse(ex.getOriginalMessage(), HttpStatus.BAD_REQUEST.value());
    }

    // Our custom Error Response class
    public static class ErrorResponse {

        private Integer code;

        private String message;

        public ErrorResponse(String message, int code) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getCode() {
            return this.code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ErrorResponse that = (ErrorResponse) o;
            return Objects.equals(code, that.code) &&
                    Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code, message);
        }

        @Override
        public String toString() {
            return "ErrorResponse{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
