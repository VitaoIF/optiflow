package com.optiflow.mapper;

import com.optiflow.dto.request.SaleRequest;
import com.optiflow.dto.response.SaleResponse;
import com.optiflow.entities.Client;
import com.optiflow.entities.Sale;
import com.optiflow.entities.SaleItem;
import com.optiflow.entities.enums.SaleStatus;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SaleMapper {

    public static Sale toEntity(SaleRequest request, List<SaleItem> items, Client client, Double totalPrice){
        return Sale.builder()
                .client(client)
                .paymentMethod(request.paymentMethod())
                .saleStatus(SaleStatus.PENDING)
                .saleItems(items)
                .totalPrice(totalPrice)
                .build();
    }

    public static SaleResponse toSaleResponse(Sale sale){
        return SaleResponse.builder()
            .id(sale.getId())
            .totalPrice(sale.getTotalPrice())
            .clientName(sale.getClient().getName())
            .clientCPF(sale.getClient().getCpf())
            .clientAddress(sale.getClient().getAddress())
            .clientPhone(sale.getClient().getPhone())

            .items(
                    sale.getSaleItems()
                            .stream()
                            .map(SaleItemMapper::toSaleItemResponse)
                            .toList()
            )
            .build();
    }
}
