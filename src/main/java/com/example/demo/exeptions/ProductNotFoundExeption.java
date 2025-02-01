package com.example.demo.exeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundExeption extends RuntimeException {

    private final static Logger logger = LoggerFactory.getLogger(ProductNotFoundExeption.class);

    public ProductNotFoundExeption(){
        super(ErrorMessages.PRODUCT_NOT_FIND.getMessage());
        logger.error("Exception: "+getClass()+"throw");
    }
}
