package com.example.user.controllers;

import com.example.user.dto.UData;
import com.example.user.models.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Controller class handling user-related operations.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userrepo;
    @GetMapping("/{email}")
    public ResponseEntity<User> get(@PathVariable("email") String email) {

        Optional<User> data = userrepo.findByEmail(email);

        return data.map(userData -> new ResponseEntity<>(userData, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        Optional<User> dataOptional = userrepo.findByEmail(email);

        if (dataOptional.isPresent()) {
            userrepo.delete(dataOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{email}")
    public ResponseEntity<User> update(@PathVariable("email") String email, @RequestBody UData udata) {
        Optional<User> dataOptional = userrepo.findByEmail(email);

        if (dataOptional.isPresent()) {
            User userData = dataOptional.get();
            // Update the fields with the new data
            userData.setFirstname(udata.getFirstName());
            userData.setLastname(udata.getLastName());
            User updatedData = userrepo.save(userData);
            return new ResponseEntity<>(updatedData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
