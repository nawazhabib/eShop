package com.habib.eshop.repository;

import com.habib.eshop.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{
    private static final List<Product> ALL_PRODUCTS = List.of(
            new Product(
                    1L,
                    "Apple iPad",
                    "Apple iPad 10.2 32Gb",
                    BigDecimal.valueOf(369.99)
            ),
            new Product(
                    2L,
                    "Headphones",
                    "Jabra Elite Blutooth Headphones",
                    BigDecimal.valueOf(249.99)
            ),
            new Product(
                    3L,
                    "Microsoft Surface Pro",
                    "Microsoft Surface Pro 7 12.3/",
                    BigDecimal.valueOf(799.99)
            ),
            new Product(
                    4L,
                    "Amazon Echo Dot",
                    "Amazon Echo Dot(3rd Gen)",
                    BigDecimal.valueOf(34.99)
            )
    );

    @Override
    public List<Product> findAllProducts(){
        return ALL_PRODUCTS;
    }
}
