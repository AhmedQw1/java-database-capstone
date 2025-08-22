package com.smartclinic.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorRegistrationDTO(
        @NotBlank String name,
        @NotBlank String specialty,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password
) {}