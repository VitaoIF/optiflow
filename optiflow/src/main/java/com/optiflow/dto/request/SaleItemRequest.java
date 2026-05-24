package com.optiflow.dto.request;

public record SaleItemRequest(
        Long productId,
        Integer quantity
) {
}
