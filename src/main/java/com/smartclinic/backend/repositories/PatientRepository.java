package com.smartclinic.backend.repositories;

import com.smartclinic.backend.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Finds a patient by their associated user's email address.
     * This uses a native SQL query to be absolutely explicit and avoid Hibernate issues.
     */
    @Query(value = "SELECT p.* FROM patients p JOIN users u ON p.user_id = u.id WHERE u.email = :email", nativeQuery = true)
    Optional<Patient> findByUserEmail(@Param("email") String email);

    Optional<Patient> findByUserEmailOrPhoneNumber(String email, String phoneNumber);
}