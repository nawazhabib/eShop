package com.habib.eshop.repository;

import com.habib.eshop.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
}
