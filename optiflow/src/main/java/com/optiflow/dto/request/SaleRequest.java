package com.optiflow.dto.request;

import com.optiflow.entities.enums.PaymentMethod;

import java.util.List;

public record SaleRequest(
        Long clientId,
        PaymentMethod paymentMethod,
        List<SaleItemRequest> items
) {
}

