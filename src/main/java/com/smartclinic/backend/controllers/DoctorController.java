package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.AppointmentResponseDTO;
import com.smartclinic.backend.dto.LoginRequestDTO;
import com.smartclinic.backend.dto.LoginResponseDTO;
import com.smartclinic.backend.models.Doctor;
import com.smartclinic.backend.models.User;
import com.smartclinic.backend.repositories.DoctorRepository;
import com.smartclinic.backend.services.AppointmentService;
import com.smartclinic.backend.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.doctorRepository = doctorRepository;
    }

    // This endpoint is now handled by AuthController, but we can leave it for now
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginDoctor(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = doctorService.loginDoctor(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<Set<LocalTime>> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Set<LocalTime> availableSlots = doctorService.getAvailableTimeSlots(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping("/me/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getMyAppointments(
            Authentication authentication,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        User currentUser = (User) authentication.getPrincipal();
        Doctor doctor = doctorRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found for the current user"));

        List<AppointmentResponseDTO> appointments = appointmentService.getAppointmentsForDoctorOnDate(doctor.getId(), date);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Search doctors by (partial) name (case-insensitive).
     * Example: GET /api/v1/doctors/search?name=john
     */
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(@RequestParam String name) {
        List<Doctor> doctors = doctorService.searchDoctorsByName(name);
        return ResponseEntity.ok(doctors);
    }
}
