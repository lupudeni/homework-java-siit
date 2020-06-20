package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.domain.ProductLine;

import java.util.List;

public interface ProductDAO {

    void save(Product product);

    Product findByCode(String productCode);

    List<Product> findByName(String productName);

//    List<Product> findByProductLine(ProductLine productLine);
//
//    List<Product> findByVendor(String vendor);

    void update (Product product);

    void delete(String productCode);
}
