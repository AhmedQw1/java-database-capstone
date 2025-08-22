package com.smartclinic.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrescriptionRequestDTO(
        @NotNull Long appointmentId,
        @NotBlank String medication,
        @NotBlank String dosage,
        String notes
) {}