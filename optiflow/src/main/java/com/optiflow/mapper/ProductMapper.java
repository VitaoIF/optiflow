package com.optiflow.mapper;

import com.optiflow.dto.request.ProductRequest;
import com.optiflow.dto.response.ProductResponse;
import com.optiflow.entities.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static Product toEntity(ProductRequest request){
        return Product.builder()
                .name(request.name())
                .brand(request.brand())
                .type(request.type())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .active(request.active())
                .build();
    }

    public static ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .type(product.getType())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
