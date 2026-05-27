package com.optiflow.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ClientRequest(
        @NotEmpty(message = "Nome é obrigatório")
        String name,
        String phone,

        @NotEmpty(message = "CPF é obrigatório")
        String cpf,
        String address
) {
}
