package com.optiflow.dto.response;

import com.optiflow.entities.enums.PaymentMethod;
import com.optiflow.entities.enums.SaleStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record SaleResponse(
        Long id,
        Double totalPrice,
        String clientName,
        String clientCPF,
        String clientPhone,
        String clientAddress,
        SaleStatus saleStatus,
        PaymentMethod paymentMethod,
        List<SaleItemResponse> items,
        LocalDate createdAt
) {
}
