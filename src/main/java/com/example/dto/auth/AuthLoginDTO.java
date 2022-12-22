package com.example.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthLoginDTO {
    @NotNull
    @Size(min = 4,message = "Login or password wrong")
    private String email;
    @NotNull
    @Size(min = 4,message = "Login or password wrong")
    private String password;

}
