package com.optiflow.dto.response;

import com.optiflow.entities.SaleItem;
import lombok.Builder;

import java.util.List;

@Builder
public record SaleResponse(
        Long id,
        Double totalPrice,
        String clientName,
        String clientCPF,
        String clientPhone,
        String clientAddress,
        List<SaleItemResponse> items
) {
}
