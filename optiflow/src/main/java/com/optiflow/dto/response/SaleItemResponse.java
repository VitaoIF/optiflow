package com.optiflow.dto.response;

import lombok.Builder;

@Builder
public record SaleItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        Double unitPrice,
        Double subtotal
) {
}
