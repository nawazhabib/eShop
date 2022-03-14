package com.habib.eshop.repository;

import com.habib.eshop.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findById(Long productId);
}
