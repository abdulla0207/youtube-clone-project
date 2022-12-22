package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer id;
    @NotBlank
    @Size(min = 5,max = 20,message = "Name is required")
    private String name;
    private LocalDateTime createdDate;

}
