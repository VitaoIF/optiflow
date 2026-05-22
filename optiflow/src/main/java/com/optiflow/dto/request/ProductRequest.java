package com.optiflow.dto.request;

import com.optiflow.entities.enums.ProductType;

public record ProductRequest(
        String name,
        String brand,
        ProductType type,
        Double price,
        Integer stockQuantity,
        Boolean active
) {
}
