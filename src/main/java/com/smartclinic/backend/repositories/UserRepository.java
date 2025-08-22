package com.smartclinic.backend.repositories;

import com.smartclinic.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Finds a user by their email address for login purposes
    Optional<User> findByEmail(String email);
}