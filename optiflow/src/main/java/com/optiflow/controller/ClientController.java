package com.optiflow.controller;

import com.optiflow.dto.request.ClientRequest;
import com.optiflow.dto.response.ClientResponse;
import com.optiflow.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> insert(@Valid @RequestBody ClientRequest clientRequest){
        ClientResponse clientResponse = clientService.insert(clientRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(clientResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> findAll(Pageable pageable){
        Page<ClientResponse> clientResponsePage = clientService.findAll(pageable);
        return ResponseEntity.ok().body(clientResponsePage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        ClientResponse clientResponse = clientService.findById(id);
        return ResponseEntity.ok().body(clientResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> updated(@PathVariable Long id,@Valid @RequestBody ClientRequest request){
        ClientResponse response = clientService.update(id, request);
        return ResponseEntity.ok().body(response);
    }
}
