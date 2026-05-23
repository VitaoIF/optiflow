package com.optiflow.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrescriptionRequest(
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
