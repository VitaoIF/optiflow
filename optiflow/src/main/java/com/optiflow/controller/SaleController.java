package com.optiflow.controller;

import com.optiflow.dto.request.SaleRequest;
import com.optiflow.dto.response.SaleResponse;
import com.optiflow.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest saleRequest){
        SaleResponse response = service.create(saleRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<SaleResponse>> findAll(Pageable pageable){
        Page<SaleResponse> saleResponse = service.findAll(pageable);
        return ResponseEntity.ok().body(saleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> findById(@PathVariable Long id){
        SaleResponse saleResponse = service.findById(id);
        return ResponseEntity.ok().body(saleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
