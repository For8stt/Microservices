package com.example.demo.product.headers;

import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false,defaultValue = "US") String region){
        if(region.equals("US")) return "Bald eagle freedom";
        if(region.equals("CAN")) return "Maple syrup";

        return "Country not supported";
    }
    @GetMapping(value = "/header/product",produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct(){
        Product product=new Product();
        product.setName("Super great product");
        product.setId(1);
        product.setDescription("The greatest product that you will ever see ever");
        return ResponseEntity.ok(product);
    }
}
