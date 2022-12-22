package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @Size(min = 3, message = "Profile name is wrong")
    private String name;

    @Size(min = 5, message = "Profile surname is wrong")
    private String surname;

    @Size(min = 4, message = "Profile password is wrong")
    private String password;

    @Email(message = "Email is wrong")
    private String email;
}
