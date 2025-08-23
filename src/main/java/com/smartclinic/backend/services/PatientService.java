package com.smartclinic.backend.services;

import com.smartclinic.backend.dto.PatientRegistrationDTO;
import com.smartclinic.backend.models.Patient;
import com.smartclinic.backend.models.User;
import com.smartclinic.backend.repositories.PatientRepository;
import com.smartclinic.backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(UserRepository userRepository, PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Patient registerPatient(PatientRegistrationDTO registrationDTO) {
        User newUser = new User();
        newUser.setEmail(registrationDTO.email());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.password()));
        newUser.setRole("ROLE_PATIENT");
        User savedUser = userRepository.save(newUser);

        Patient newPatient = new Patient();
        newPatient.setName(registrationDTO.name());
        newPatient.setPhoneNumber(registrationDTO.phoneNumber());
        newPatient.setDateOfBirth(registrationDTO.dateOfBirth());
        newPatient.setUser(savedUser);

        return patientRepository.save(newPatient);
    }
}