package com.smartclinic.backend.services;

import com.smartclinic.backend.dto.LoginRequestDTO;
import com.smartclinic.backend.dto.LoginResponseDTO;
import com.smartclinic.backend.models.Doctor;
import com.smartclinic.backend.repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public DoctorService(DoctorRepository doctorRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.doctorRepository = doctorRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Validates doctor login credentials and returns a structured response (JWT).
     */
    public LoginResponseDTO loginDoctor(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        String jwt = tokenService.generateToken(authentication);
        return new LoginResponseDTO(jwt);
    }

    /**
     * Returns the set of available time slots for a doctor.
     * Note: This returns the doctor's general availability. A real-world app would
     * filter this against already booked appointments for the given date.
     */
    public Set<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));

        // For this project, we return the doctor's general availability.
        return doctor.getAvailableTimes();
    }

    /**
     * Search for doctors by (partial) name, case-insensitive.
     */
    public List<Doctor> searchDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }
}
