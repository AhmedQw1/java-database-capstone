package com.smartclinic.backend.dto;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        LocalDateTime appointmentTime,
        String status,
        String reason,
        String patientName
) {}