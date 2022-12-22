package com.example.controller;

import com.example.dto.ChangeDTO;
import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/change")
    public ResponseEntity<ChangeDTO> change(@Valid @RequestBody ChangeDTO dto){
        log.info("Change Password -> " + dto);
        ChangeDTO response = profileService.change(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/email")
    public ResponseEntity<?> updateEmail(@Valid @RequestBody ProfileDTO profileDTO) {
        int result = profileService.updateEmail(profileDTO);

        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ProfileDTO profileDTO) {
        int result = profileService.updateUser(profileDTO);

        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/photo")
    public ResponseEntity<?> updateAttach(@Valid @RequestBody ProfileDTO profileDTO) {
        int result = profileService.updateAttach(profileDTO);

        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Get", description = "Method used for get profile info")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        log.info("/{id} {}", id);
        return ResponseEntity.ok(profileService.get(id));
    }


    /*ADMIN*/
    @PostMapping("/admin/create")
    public ResponseEntity<ProfileDTO> save(@Valid @RequestBody ProfileDTO profileDTO) {
        log.info("Create Profile -> "+profileDTO);
        ProfileDTO profile = profileService.create(profileDTO);
        return ResponseEntity.ok(profile);
    }

}
