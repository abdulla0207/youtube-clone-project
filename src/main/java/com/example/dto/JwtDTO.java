package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class JwtDTO {
    private String id;
    private String email;
    private ProfileRole role;
}
