package com.optiflow.controller;

import com.optiflow.dto.request.ProductRequest;
import com.optiflow.dto.response.ProductResponse;
import com.optiflow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> insert(@RequestBody ProductRequest request){
        ProductResponse response = productService.insert(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable){
        Page<ProductResponse> productResponse = productService.findAll(pageable);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        ProductResponse productResponse = productService.findById(id);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping(value = "{/id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{/id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request){
        ProductResponse productResponse = productService.update(id, request);
        return ResponseEntity.ok().body(productResponse);
    }
}
