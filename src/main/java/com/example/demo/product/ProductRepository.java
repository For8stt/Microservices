package com.example.demo.product;

import com.example.demo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContaining(String string);

    @Query("SELECT p from Product p where p.name like %:keyword% or p.description like %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String name);

}
