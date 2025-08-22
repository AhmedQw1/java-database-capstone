package com.smartclinic.backend.services;

import com.smartclinic.backend.dto.DoctorRegistrationDTO;
import com.smartclinic.backend.models.Doctor;
import com.smartclinic.backend.models.User;
import com.smartclinic.backend.repositories.DoctorRepository;
import com.smartclinic.backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Doctor registerDoctor(DoctorRegistrationDTO registrationDTO) {
        // First, create the user for login
        User newUser = new User();
        newUser.setEmail(registrationDTO.email());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.password()));
        newUser.setRole("ROLE_DOCTOR");
        User savedUser = userRepository.save(newUser);

        // Then, create the doctor profile linked to the user
        Doctor newDoctor = new Doctor();
        newDoctor.setName(registrationDTO.name());
        newDoctor.setSpecialty(registrationDTO.specialty());
        newDoctor.setUser(savedUser);

        return doctorRepository.save(newDoctor);
    }
}