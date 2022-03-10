package com.habib.eshop.service;

import com.habib.eshop.dto.ProductDTO;
import com.habib.eshop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    private static final ProductDTO APPLE_PAD = new ProductDTO(
            "Apple iPad",
            "Apple iPad 12.3 16GB",
            BigDecimal.valueOf(345.22));

    private static final ProductDTO HEADPHONE = new ProductDTO(
            "Headphones",
            "Javra Elite Bluetooth Headphones",
            BigDecimal.valueOf(545.44));

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() throws Exception{
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }
    @Test
    public void testFindAllProductSortedByName(){
        when(productRepository.findAllProducts())
                .thenReturn(
                        List.of(HEADPHONE, APPLE_PAD)
                );
        var sortedByName = productService.findAllProductSortedByName();
        Assert.assertEquals(APPLE_PAD.getName(),sortedByName.get(0).getName());
        Assert.assertEquals(HEADPHONE.getName(), sortedByName.get(1).getName());
    }
}
