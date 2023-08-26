package com.example.user.repository;

import java.util.Optional;
import com.example.user.models.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for managing user entities.
 */
@Hidden
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
