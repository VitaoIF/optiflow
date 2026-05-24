package com.optiflow.service;

import com.optiflow.dto.request.PrescriptionRequest;
import com.optiflow.dto.response.PrescriptionResponse;
import com.optiflow.entities.Client;
import com.optiflow.entities.Prescription;
import com.optiflow.mapper.PrescriptionMapper;
import com.optiflow.repository.ClientRepository;
import com.optiflow.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public PrescriptionResponse insert(PrescriptionRequest request){
        Client client = clientRepository
                .findById(request.clientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Prescription prescription = PrescriptionMapper.toEntity(request, client);
        prescriptionRepository.save(prescription);
        return PrescriptionMapper.toPrescriptionResponse(prescription);
    }

    public PrescriptionResponse findById(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescrição não encontrada"));
        return PrescriptionMapper.toPrescriptionResponse(prescription);
    }

    public Page<PrescriptionResponse> findAll(Pageable pageable){
        Page<Prescription> prescriptions = prescriptionRepository.findAll(pageable);
        return prescriptions.map(PrescriptionMapper::toPrescriptionResponse);
    }

    public void delete(Long id){
        prescriptionRepository.deleteById(id);
    }

    public PrescriptionResponse update(Long id, PrescriptionRequest request){
        Prescription entity = prescriptionRepository.getReferenceById(id);
        prescriptionUpdate(entity, request);
        Prescription updated = prescriptionRepository.save(entity);
        return PrescriptionMapper.toPrescriptionResponse(updated);
    }

    private void prescriptionUpdate(Prescription prescription, PrescriptionRequest request){
        prescription.setOdSphere(request.odSphere());
        prescription.setOsSphere(request.osSphere());
        prescription.setOdCylinder(request.odCylinder());
        prescription.setOsCylinder(request.osCylinder());
        prescription.setAxis(request.axis());
        prescription.setAddition(request.addition());
        prescription.setDoctorName(request.doctorName());
        prescription.setDate(request.date());
    }
}
