package com.optiflow.dto.request;

import com.optiflow.entities.enums.ProductType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotEmpty(message = "Nome do produto é obrigatório")
        String name,
        String brand,

        @NotNull(message = "Tipo do produto é obrigatório")
        ProductType type,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser maior que 0")
        Double price,

        @NotNull(message = "Informar a quantiade em estoque é obrigatório")
        @PositiveOrZero(message = "Quantidade deve ser 0 ou maior")
        Integer stockQuantity,
        Boolean active
) {
}
