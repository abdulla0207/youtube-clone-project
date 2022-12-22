package com.example.dto.auth;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    private String name;
    private String surname;
    private ProfileRole role;
    private String token;
}
