package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.domain.ProductLine;

import java.util.List;

public interface ProductDAO {

    Product save(Product product);

    Product findByCode(String productCode);

    List<Product> findByName(String productName);

    boolean update (Product product);

    boolean delete(String productCode);
}
