package com.smartclinic.backend.repositories;

import com.smartclinic.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Finds all appointments for a specific doctor within a given time range (e.g., a single day).
     * This derived query is essential for fetching a doctor's daily schedule.
     */
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
}