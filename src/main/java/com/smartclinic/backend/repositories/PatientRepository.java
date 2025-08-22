package com.smartclinic.backend.repositories;

import com.smartclinic.backend.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Patient entities.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Finds a patient by their associated user's email address.
     * This is a derived query method; Spring Data JPA generates the query from the method name.
     *
     * @param email The email of the user associated with the patient.
     * @return An Optional containing the found patient, or an empty Optional if not found.
     */
    Optional<Patient> findByUserEmail(String email);

    /**
     * Finds a patient by their associated user's email address OR their phone number.
     * This is another example of a derived query with an 'Or' condition.
     *
     * @param email The user's email.
     * @param phoneNumber The patient's phone number.
     * @return An Optional containing the found patient, or an empty Optional if not found.
     */
    Optional<Patient> findByUserEmailOrPhoneNumber(String email, String phoneNumber);
}