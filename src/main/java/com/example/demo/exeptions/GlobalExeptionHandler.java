package com.example.demo.exeptions;

import com.example.demo.product.model.ErrorResponse;
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
}
