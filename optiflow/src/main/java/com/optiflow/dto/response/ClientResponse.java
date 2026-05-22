package com.optiflow.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientResponse(
        Long id,
        String name,
        String phone,
        String cpf,
        String address,
        LocalDate createdAt
) {
}
