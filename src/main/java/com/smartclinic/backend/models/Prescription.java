package com.smartclinic.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    @NotNull
    private Appointment appointment;

    @NotBlank
    @Column(nullable = false)
    private String medication;

    @NotBlank
    @Column(nullable = false)
    private String dosage;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @NotNull
    @Column(nullable = false)
    private LocalDate issueDate;
}