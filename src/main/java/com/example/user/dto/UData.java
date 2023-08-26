package com.example.user.dto;

import lombok.*;
/**
 * Data Transfer Object (DTO) class representing user data.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UData {
    String address;
    String nic;
    String email;
    String firstName;
    String lastName;
}
