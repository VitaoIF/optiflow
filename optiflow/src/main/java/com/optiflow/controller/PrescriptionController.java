package com.optiflow.controller;

import com.optiflow.dto.request.PrescriptionRequest;
import com.optiflow.dto.response.PrescriptionResponse;
import com.optiflow.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionResponse> insert(@Valid @RequestBody PrescriptionRequest request){
        PrescriptionResponse prescriptionResponse = prescriptionService.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(prescriptionResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(prescriptionResponse);
    }

    @GetMapping
    public ResponseEntity<Page<PrescriptionResponse>> findAll(Pageable pageable){
        Page<PrescriptionResponse> prescriptionResponses = prescriptionService.findAll(pageable);
        return ResponseEntity.ok().body(prescriptionResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponse> findById(@PathVariable Long id){
        PrescriptionResponse prescriptionResponse = prescriptionService.findById(id);
        return ResponseEntity.ok().body(prescriptionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        prescriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionResponse> update(@PathVariable Long id, @Valid @RequestBody PrescriptionRequest request){
        PrescriptionResponse prescriptionResponse = prescriptionService.update(id, request);
        return ResponseEntity.ok().body(prescriptionResponse);
    }
}
