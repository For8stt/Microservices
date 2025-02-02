package com.example.demo.product.services;

import com.example.demo.Command;
import com.example.demo.exeptions.ProductNotFoundExeption;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import com.example.demo.product.validators.ProductValidator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
//    @CacheEvict(value = "productCache",key = "#updateProductCommand.getId()")
    @CachePut(value = "productCache",key = "#updateProductCommand.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> productOptional=productRepository.findById(updateProductCommand.getId());
        if(productOptional.isPresent()){
            Product product=updateProductCommand.getProduct();
            product.setId(updateProductCommand.getId());

//            ProductValidator.execute(product);
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));

        }

        throw new ProductNotFoundExeption();
    }
}
