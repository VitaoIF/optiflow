package com.optiflow.dto.response;

import lombok.Builder;

@Builder
public record SaleItemResponse(
        String productName,
        Integer quantity,
        Double unitPrice,
        Double subtotal
) {
}
