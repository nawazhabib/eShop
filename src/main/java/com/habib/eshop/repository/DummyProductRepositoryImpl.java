package com.habib.eshop.repository;

import com.habib.eshop.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository {
    @Override
    public List<ProductDTO> findAllProducts(){
        return List.of(
                new ProductDTO(
                        "Apple iPad",
                        "Apple iPad 12.3 16GB",
                        BigDecimal.valueOf(345.22)),
                new ProductDTO(
                        "Headphones",
                        "Javra Elite Bluetooth Headphones",
                        BigDecimal.valueOf(545.44)),
                new ProductDTO(
                        "Microsoft Surface Pro",
                        "Microsoft Surface Pro 7.3.4",
                        BigDecimal.valueOf(788.44)),
                new ProductDTO(
                        "Amazon Echo Dot",
                        "Amazon Echo Dot (3rd Gen)",
                        BigDecimal.valueOf(34.22))
        );
    }
}
