package com.optiflow.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrescriptionRequest(
        @NotNull(message = "Cliente é obrigatório")
        Long clientId,

        @NotNull(message = "OD Sphere é obrigatório")
        BigDecimal odSphere,

        @NotNull(message = "OS Sphere é obrigatório")
        BigDecimal osSphere,

        @NotNull(message = "OD Cylinder é obrigatório")
        BigDecimal odCylinder,

        @NotNull(message = "OS Cylinder é obrigatório")
        BigDecimal osCylinder,

        @NotNull(message = "Axis é obrigatório")
        @DecimalMin(value = "0", message = "Axis deve ser maior ou igual a 0")
        @DecimalMax(value = "180", message = "Axis deve ser menor ou igual a 180")
        BigDecimal axis,

        @NotNull(message = "Addition é obrigatório")
        @PositiveOrZero(message = "Quantidade deve ser 0 ou maior")
        BigDecimal addition,
        String doctorName,

        @NotNull(message = "Data é obrigatória")
        @PastOrPresent(message = "Data não pode ser futura")
        LocalDate date
) {
}
