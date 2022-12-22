package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotBlank @Size(min = 5,max = 20,message = "Name is required")
    private String name;
    @NotBlank @Size(min = 5,max = 20,message = "Surname is required")
    private String surname;
    @NotBlank @Size(min = 4,max = 20,message = "Email is required")
    private String email;
    @NotBlank @Size(min = 4,max = 50,message = "Password must contain 4 characters")
    private String password;
    private ProfileRole role;
    private ProfileStatus status;
    private String attachId;
    private AttachDTO image;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}


