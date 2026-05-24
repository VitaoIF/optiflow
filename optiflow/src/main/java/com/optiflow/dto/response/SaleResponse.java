package com.optiflow.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SaleResponse(
        Long id,
        Double totalPrice,
        String clientName,
        List<SaleItemResponse> items
) {
}
