package com.optiflow.mapper;

import com.optiflow.dto.request.PrescriptionRequest;
import com.optiflow.dto.response.PrescriptionResponse;
import com.optiflow.entities.Client;
import com.optiflow.entities.Prescription;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PrescriptionMapper {

    public static Prescription toEntity(PrescriptionRequest request, Client client){
        return Prescription.builder()
                .odSphere(request.odSphere())
                .osSphere(request.osSphere())
                .odCylinder(request.odCylinder())
                .osCylinder(request.osCylinder())
                .axis(request.axis())
                .addition(request.addition())
                .doctorName(request.doctorName())
                .date(request.date())
                .client(client)
                .build();
    }

    public static PrescriptionResponse toPrescriptionResponse(Prescription prescription){
        return PrescriptionResponse.builder()
                .id(prescription.getId())
                .clientId(prescription.getClient().getId())
                .clientName(prescription.getClient().getName())
                .clientCPF(prescription.getClient().getCpf())
                .odSphere(prescription.getOdSphere())
                .osSphere(prescription.getOsSphere())
                .odCylinder(prescription.getOdCylinder())
                .osCylinder(prescription.getOsCylinder())
                .axis(prescription.getAxis())
                .addition(prescription.getAddition())
                .doctorName(prescription.getDoctorName())
                .date(prescription.getDate())
                .build();
    }


}
