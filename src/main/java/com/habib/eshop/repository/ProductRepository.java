package com.habib.eshop.repository;

import com.habib.eshop.dto.ProductDTO;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAllProducts();
}
