package com.example.controller;

import com.example.dto.auth.AuthLoginDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.dto.RegistrationDTO;
import com.example.enums.Language;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    @Operation(summary = "Login method", description = "Method used for profile registration")
    public ResponseEntity<?> login(@RequestBody AuthLoginDTO dto,
                                   @RequestHeader("Accept-Language") Language language){
        log.info("Login + {} "+ dto);
      AuthResponseDTO authResponseDTO = authService.login(dto,language);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "Registration method", description = "Method used for profile registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDTO registrationDTO){
        String response = authService.register(registrationDTO);
        log.info("Registration + {} "+ registrationDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verification/email/{jtwToken}")
    @Operation(summary = "email verification method", description = "Method used for email verification")
    public ResponseEntity<?> emailVerification(@PathVariable("jtwToken") String jwt) {
        log.info("Registration + {} " + jwt);
        String response = authService.verification(jwt);
        return ResponseEntity.ok(response);
    }
}
