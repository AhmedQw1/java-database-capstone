package com.smartclinic.backend.controllers;

import com.smartclinic.backend.dto.DoctorRegistrationDTO;
import com.smartclinic.backend.models.Doctor;
import com.smartclinic.backend.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')") // Only users with ROLE_ADMIN can access this
    public ResponseEntity<Doctor> registerDoctor(@Valid @RequestBody DoctorRegistrationDTO registrationDTO) {
        Doctor newDoctor = adminService.registerDoctor(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
    }
}