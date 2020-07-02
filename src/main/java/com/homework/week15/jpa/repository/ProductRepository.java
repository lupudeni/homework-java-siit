package com.homework.week15.jpa.repository;

import com.homework.week15.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findProductByProductCode(String productCode);

    void deleteProductByProductCode(String productCode);
}
