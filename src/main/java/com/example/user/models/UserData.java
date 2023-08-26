package com.example.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing user data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_data")

public class UserData {
    @Id
    @GeneratedValue
    private Integer id;
    private String address;
    private String nic;
    private String email;
}
