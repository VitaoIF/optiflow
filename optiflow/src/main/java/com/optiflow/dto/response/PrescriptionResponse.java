package com.optiflow.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PrescriptionResponse(
        Long id,
        Long clientId,
        BigDecimal odSphere,
        BigDecimal osSphere,
        BigDecimal odCylinder,
        BigDecimal osCylinder,
        BigDecimal axis,
        BigDecimal addition,
        String doctorName,
        LocalDate date
) {
}
