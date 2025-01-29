package com.example.demo.exeptions;

import org.aspectj.bridge.IMessage;

public enum ErrorMessages {
    PRODUCT_NOT_FIND("Product not found ('_')"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative");

    private final String message;

    ErrorMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }

}
