package com.habib.eshop.service;

import com.habib.eshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductSortedByName();
}
