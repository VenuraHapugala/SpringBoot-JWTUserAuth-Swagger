package com.example.user.repository;

import java.util.Optional;
import com.example.user.models.UserData;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing user data entities.
 */
@Hidden
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    Optional<UserData> findByEmail(String email);
}
