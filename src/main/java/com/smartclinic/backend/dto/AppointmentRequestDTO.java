package com.smartclinic.backend.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        @NotNull Long doctorId,
        @NotNull Long patientId,
        @NotNull @FutureOrPresent LocalDateTime appointmentTime,
        String reason
) {}