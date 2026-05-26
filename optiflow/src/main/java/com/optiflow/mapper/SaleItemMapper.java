package com.optiflow.mapper;

import com.optiflow.dto.request.SaleItemRequest;
import com.optiflow.dto.response.SaleItemResponse;
import com.optiflow.entities.Product;
import com.optiflow.entities.SaleItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SaleItemMapper {

    public static SaleItem toEntity(SaleItemRequest saleItemRequest, Product product){
        return SaleItem.builder()
                .product(product)
                .quantity(saleItemRequest.quantity())
                .unitPrice(product.getPrice())
                .build();
    }

    public static SaleItemResponse toSaleItemResponse(SaleItem saleItem){
        return SaleItemResponse.builder()
                .productId(saleItem.getProduct().getId())
                .productName(saleItem.getProduct().getName())
                .quantity(saleItem.getQuantity())
                .unitPrice(saleItem.getUnitPrice())
                .subtotal(
                        saleItem.getQuantity() * saleItem.getUnitPrice()
                )
                .build();
    }
}
