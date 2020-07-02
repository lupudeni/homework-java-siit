package com.homework.week15.jpa.service;

import com.homework.week15.jpa.entity.Product;
import com.homework.week15.jpa.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(String productCode) {
        productRepository.deleteProductByProductCode(productCode);
    }

    public Optional<Product> findProductByCode(String productCode) {
        return Optional.ofNullable(productRepository.findProductByProductCode(productCode));
    }

    public boolean update(Product product) {
        Optional<Product> foundProduct = findProductByCode(product.getProductCode());
        if (foundProduct.isPresent()) {
            save(product);
            return true;
        }
        return false;
    }
}
