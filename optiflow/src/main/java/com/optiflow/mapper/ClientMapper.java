package com.optiflow.mapper;

import com.optiflow.dto.request.ClientRequest;
import com.optiflow.dto.response.ClientResponse;
import com.optiflow.entities.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientMapper {

    public static Client toEntity(ClientRequest request){
        return Client.builder()
                .name(request.name())
                .cpf(request.cpf())
                .phone(request.phone())
                .address(request.address())
                .build();
    }

    public static ClientResponse toClientResponse(Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .cpf(client.getCpf())
                .address(client.getAddress())
                .createdAt(client.getCreatedAt())
                .build();
    }
}
