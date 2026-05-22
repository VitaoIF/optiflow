package com.optiflow.dto.response;

import com.optiflow.entities.enums.ProductType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String brand,
        ProductType type,
        Double price,
        Integer stockQuantity,
        Boolean active,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
