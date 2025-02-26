package edu.unitru.clientems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException notFoundException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse notFoundExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage());
    }

}
