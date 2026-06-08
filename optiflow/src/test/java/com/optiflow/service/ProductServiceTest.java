package com.optiflow.service;

import com.optiflow.dto.request.ProductRequest;
import com.optiflow.dto.response.ProductResponse;
import com.optiflow.entities.Product;
import com.optiflow.entities.enums.ProductType;
import com.optiflow.exceptions.custom.ProductNotFoundException;
import com.optiflow.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductRequest request;
    private Product product;

    @BeforeEach
    void setUp() {
        request = new ProductRequest(
                "Óculos",
                "Oakley",
                ProductType.FRAME,
                300.0,
                2,
                true
        );

        product = new Product();
        product.setId(1L);
        product.setName("Óculos");
        product.setBrand("Oakley");
        product.setType(ProductType.FRAME);
        product.setPrice(300.0);
        product.setStockQuantity(2);
        product.setActive(true);
    }


    @Test
    @DisplayName("Should insert product sucessfully")
    void shouldInsertProductSuccessfully(){

        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        ProductResponse productResponse = productService.insert(request);
        assertNotNull(productResponse);
        assertEquals(1L, productResponse.id());
        assertEquals("Óculos", productResponse.name());
        assertEquals("Oakley", productResponse.brand());
        assertEquals(ProductType.FRAME, productResponse.type());
        assertEquals(300.00, productResponse.price());
        assertEquals(2, productResponse.stockQuantity());
        assertEquals(Boolean.TRUE, productResponse.active());
    }

    @Test
    @DisplayName("Should return product by id")
    void shouldReturnProductWhenIdExists(){

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse productResponse = productService.findById(1L);
        assertEquals("Óculos", productResponse.name());
    }

    @Test
    @DisplayName("Should return exception when product id not found")
    void shouldReturnExceptionWhenIdNotExists(){
        when(productRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.findById(2L)
        );
    }



}