package com.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailHistoryDTO {

    private Integer id;
    @NotBlank
    @Size(min = 5,max = 20,message = "title is required")
    private String title;
    @NotBlank
    @Size(min = 5,message = "messsage is required")
    private String message;
    @NotBlank
    private String toEmail;
}
