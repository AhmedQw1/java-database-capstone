package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.LoginRequestDTO;
import com.smartclinic.backend.dto.LoginResponseDTO;
import com.smartclinic.backend.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginDoctor(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = doctorService.loginDoctor(loginRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Exposes a GET endpoint for doctor availability using dynamic parameters.
     * The response is wrapped in a ResponseEntity for structured control.
     * Example URL: GET /api/v1/doctors/1/availability?date=2025-09-15
     */
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<Set<LocalTime>> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Set<LocalTime> availableSlots = doctorService.getAvailableTimeSlots(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }
}