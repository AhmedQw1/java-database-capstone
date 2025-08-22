package com.smartclinic.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a doctor in the system.
 * This class is a JPA entity mapped to the 'doctors' table in the database.
 */
@Entity
@Table(name = "doctors")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation for a no-argument constructor
@AllArgsConstructor // Lombok annotation for a constructor with all arguments
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Specialty cannot be blank")
    @Column(nullable = false, length = 100)
    private String specialty;

    // Establishes a one-to-one relationship with the User entity.
    // Each doctor profile is linked to a single user account for login.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // This annotation maps a collection of simple types (LocalTime) to a separate table.
    // JPA will automatically create a 'doctor_available_times' table to store these.
    // This perfectly handles a doctor having multiple available time slots.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "available_time", nullable = false)
    private Set<LocalTime> availableTimes = new HashSet<>();

}