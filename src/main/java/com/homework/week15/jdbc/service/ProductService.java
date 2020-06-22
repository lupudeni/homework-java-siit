package com.homework.week15.jdbc.service;

import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.repository.ProductDAO;
import com.homework.week15.jdbc.repository.ProductDAOImpl;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    public Optional<Product> save(Product product) {
        return Optional.ofNullable(productDAO.save(product));
    }

    public Optional<Product> findByCode(String productCode) {
        return Optional.ofNullable(productDAO.findByCode(productCode));
    }

    public List<Product> findByName(String productName) {
        return productDAO.findByName(productName);
    }

    public boolean update(Product product) {
        return productDAO.update(product);
    }

    public boolean delete(String productCode) {
        return productDAO.delete(productCode);
    }


}
