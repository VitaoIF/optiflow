package com.optiflow.service;

import com.optiflow.dto.request.ClientRequest;
import com.optiflow.dto.response.ClientResponse;
import com.optiflow.entities.Client;
import com.optiflow.mapper.ClientMapper;
import com.optiflow.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientResponse insert(ClientRequest request){
        Client client = ClientMapper.toEntity(request);

        Client saved = repository.save(client);

        return ClientMapper.toClientResponse(saved);
    }

    public Page<ClientResponse> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientMapper::toClientResponse);
    }

    public ClientResponse findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClientMapper.toClientResponse(client);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public ClientResponse update(Long id, ClientRequest request){
        Client entity = repository.getReferenceById(id);
        updateClient(entity, request);
        Client updated = repository.save(entity);
        return ClientMapper.toClientResponse(updated);
    }

    private void updateClient(Client entity, ClientRequest request){
        entity.setUpdatedAt(LocalDate.now());
        entity.setCpf(request.cpf());
        entity.setName(request.name());
        entity.setCpf(request.cpf());
        entity.setAddress(request.address());
        entity.setPhone(request.phone());
    }
}
