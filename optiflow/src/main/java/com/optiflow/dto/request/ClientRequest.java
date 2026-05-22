package com.optiflow.dto.request;

public record ClientRequest(
        String name,
        String phone,
        String cpf,
        String address
) {
}
