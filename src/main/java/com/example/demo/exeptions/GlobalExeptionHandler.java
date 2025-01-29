package com.example.demo.exeptions;

import com.example.demo.product.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(ProductNotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundExeption exeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exeption.getMessage()));
    }

    @ExceptionHandler(ProductNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotValidException(ProductNotValidException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductValidConstrains(ConstraintViolationException exception){
        return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
    }
}
