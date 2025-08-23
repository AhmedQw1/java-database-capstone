package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.AppointmentRequestDTO;
import com.smartclinic.backend.models.Appointment;
import com.smartclinic.backend.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody AppointmentRequestDTO request, Authentication authentication) {
        Appointment newAppointment = appointmentService.bookAppointment(request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }}