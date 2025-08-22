package com.smartclinic.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents a user in the system for authentication and authorization purposes.
 * Implements Spring Security's UserDetails interface to integrate with its security framework.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Role cannot be blank")
    @Column(nullable = false)
    private String role; // e.g., "ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_PATIENT"


    // --- UserDetails Interface Methods ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This provides the user's role to Spring Security
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        // Our system uses email as the username
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is always valid
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are never expired
    }



    @Override
    public boolean isEnabled() {
        return true; // Account is always enabled
    }
}