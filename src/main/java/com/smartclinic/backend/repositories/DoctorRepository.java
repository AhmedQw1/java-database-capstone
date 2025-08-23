package com.smartclinic.backend.repositories;

import com.smartclinic.backend.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Finds a doctor profile using the associated user's ID
    Optional<Doctor> findByUserId(Long userId);

    // Search doctors by (partial) name, case-insensitive
    List<Doctor> findByNameContainingIgnoreCase(String name);
}
