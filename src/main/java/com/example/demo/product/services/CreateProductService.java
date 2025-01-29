package com.example.demo.product.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo.Command;
import com.example.demo.exeptions.ErrorMessages;
import com.example.demo.exeptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.validators.ProductValidator;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

//        ProductValidator.execute(product);

        Product savedProduct=productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }

}
