package com.example.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(){
        super(ErrorMessages.PRODUCT_NOT_FIND.getMessage());
    }
}
