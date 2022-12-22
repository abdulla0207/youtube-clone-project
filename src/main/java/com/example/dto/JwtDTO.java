package com.example.dto;

import com.example.enums.ProfileRole;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtDTO {
    private String username;
    private ProfileRole role;
}
