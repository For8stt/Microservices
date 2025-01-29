package com.example.demo.product.validators;

import com.example.demo.exeptions.ErrorMessages;
import com.example.demo.exeptions.ProductNotValidException;
import com.example.demo.product.model.Product;
import io.micrometer.common.util.StringUtils;

public class ProductValidator {
    public ProductValidator(){}
    public static void execute(Product product){
        if(StringUtils.isEmpty(product.getName())){
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }
        if(product.getDescription().length()<20){
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }
        if(product.getPrice()==null || product.getPrice()<0.00){
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }
    }

}
