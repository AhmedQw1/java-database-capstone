package com.smartclinic.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents an appointment scheduled at the clinic.
 * This entity links a Doctor and a Patient at a specific time.
 */
@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Defines a many-to-one relationship with Doctor.
    // Many appointments can be associated with one doctor.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Appointment must be assigned to a doctor.")
    private Doctor doctor;

    // Defines a many-to-one relationship with Patient.
    // Many appointments can be associated with one patient.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Appointment must be assigned to a patient.")
    private Patient patient;

    @NotNull(message = "Appointment time cannot be null.")
    @FutureOrPresent(message = "Appointment time must be in the present or future.")
    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @NotBlank(message = "Status cannot be blank.")
    @Column(nullable = false)
    private String status; // e.g., "SCHEDULED", "COMPLETED", "CANCELLED"

    @Column(columnDefinition = "TEXT")
    private String reason;
}