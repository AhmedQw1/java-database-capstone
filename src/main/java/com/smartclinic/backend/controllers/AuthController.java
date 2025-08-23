package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.LoginRequestDTO;
import com.smartclinic.backend.dto.LoginResponseDTO;
import com.smartclinic.backend.dto.PatientRegistrationDTO;
import com.smartclinic.backend.models.Patient;
import com.smartclinic.backend.services.AuthService;
import com.smartclinic.backend.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final PatientService patientService; // The service for patient logic

    // The corrected constructor that injects BOTH services
    public AuthController(AuthService authService, PatientService patientService) {
        this.authService = authService;
        this.patientService = patientService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/patient")
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody PatientRegistrationDTO registrationDTO) {
        Patient newPatient = patientService.registerPatient(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }
}