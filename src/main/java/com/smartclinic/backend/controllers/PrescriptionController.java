package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.PrescriptionRequestDTO;
import com.smartclinic.backend.models.Prescription;
import com.smartclinic.backend.services.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * POST endpoint to save a prescription.
     * The request body is validated, and the response is a structured ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody PrescriptionRequestDTO request) {
        Prescription newPrescription = prescriptionService.createPrescription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPrescription);
    }
}