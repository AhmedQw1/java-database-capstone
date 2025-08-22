package com.smartclinic.backend.services;

import com.smartclinic.backend.dto.PrescriptionRequestDTO;
import com.smartclinic.backend.models.Appointment;
import com.smartclinic.backend.models.Prescription;
import com.smartclinic.backend.repositories.AppointmentRepository;
import com.smartclinic.backend.repositories.PrescriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Prescription createPrescription(PrescriptionRequestDTO request) {
        Appointment appointment = appointmentRepository.findById(request.appointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + request.appointmentId()));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedication(request.medication());
        prescription.setDosage(request.dosage());
        prescription.setNotes(request.notes());
        prescription.setIssueDate(LocalDate.now());

        return prescriptionRepository.save(prescription);
    }
}