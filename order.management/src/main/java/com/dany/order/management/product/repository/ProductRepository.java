package com.dany.order.management.product.repository;

import com.dany.order.management.product.repository.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> searchProductByName(String nameProduct);
}
