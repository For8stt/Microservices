package com.example.demo.exeptions;

import org.aspectj.bridge.IMessage;

public enum ErrorMessages {
    PRODUCT_NOT_FIND("Product not found ('_')");

    private final String message;

    ErrorMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }

}
