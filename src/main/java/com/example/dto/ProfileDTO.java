package com.example.dto;

import com.example.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String attachId;
    private ProfileRole role;
}
