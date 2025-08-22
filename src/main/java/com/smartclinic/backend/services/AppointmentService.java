package com.smartclinic.backend.services;

import com.smartclinic.backend.dto.AppointmentRequestDTO;
import com.smartclinic.backend.models.Appointment;
import com.smartclinic.backend.models.Doctor;
import com.smartclinic.backend.models.Patient;
import com.smartclinic.backend.repositories.AppointmentRepository;
import com.smartclinic.backend.repositories.DoctorRepository;
import com.smartclinic.backend.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Implements the booking method that saves an appointment.
     * It fetches the doctor and patient, creates a new appointment, and saves it.
     */
    @Transactional
    public Appointment bookAppointment(AppointmentRequestDTO request) {
        Doctor doctor = doctorRepository.findById(request.doctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + request.doctorId()));

        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + request.patientId()));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(request.appointmentTime());
        appointment.setReason(request.reason());
        appointment.setStatus("SCHEDULED");

        return appointmentRepository.save(appointment);
    }

    /**
     * Defines a method to retrieve appointments for a doctor on a specific date.
     * It uses the custom repository method to find appointments within the date's time range.
     */
    public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, startOfDay, endOfDay);
    }
}