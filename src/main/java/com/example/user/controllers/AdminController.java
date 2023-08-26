package com.example.user.controllers;

import com.example.user.dto.UData;
import com.example.user.models.UserData;
import com.example.user.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Controller class handling administrative operations on user data.
 */
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {
    private final  UserDataRepository userdatarepo;
    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<UserData> get(@PathVariable("email") String email) {

        Optional<UserData> data = userdatarepo.findByEmail(email);

        return data.map(userData -> new ResponseEntity<>(userData, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin:create')")

    public ResponseEntity<UserData> post(@RequestBody UData udata) {
        try {
            var user = UserData.builder()
                    .address(udata.getAddress())
                    .email(udata.getEmail())
                    .nic(udata.getNic())
                    .build();
            var _data = userdatarepo.save(user);
            System.out.println(user);
            System.out.println(_data);
            return new ResponseEntity<>(_data, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{email}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UserData> update(@PathVariable("email") String email, @RequestBody UData udata) {
        Optional<UserData> dataOptional = userdatarepo.findByEmail(email);

        if (dataOptional.isPresent()) {
            UserData userData = dataOptional.get();

            // Update the fields with the new data
            userData.setAddress(udata.getAddress());
            userData.setNic(udata.getNic());
            UserData updatedData = userdatarepo.save(userData);
            return new ResponseEntity<>(updatedData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{email}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        Optional<UserData> dataOptional = userdatarepo.findByEmail(email);
        if (dataOptional.isPresent()) {
            userdatarepo.delete(dataOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
