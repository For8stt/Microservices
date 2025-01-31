package com.example.demo;

import com.example.demo.exeptions.ProductNotFoundExeption;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {
    @Mock //what to mock the response
    private ProductRepository productRepository;
    @InjectMocks // what we are testing
    private GetProductService getProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product description which is at least 20 characters");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //When
        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        //Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        verify(productRepository, times(1)).findById(1);


    }
    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_not_found_exception(){
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundExeption.class, ()-> getProductService.execute(1));
        verify(productRepository,times(1)).findById(1);


    }



}
