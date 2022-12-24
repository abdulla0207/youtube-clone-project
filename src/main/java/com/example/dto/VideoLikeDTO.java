package com.example.dto;

import com.example.entity.ProfileEntity;
import com.example.entity.VideoTagEntity;
import com.example.enums.VideoLikeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoLikeDTO {

    private Integer id;

    @NotBlank
    @Size(message = "Profile REQUIRED")
    private Integer profileId;


    @NotBlank
    @Size(message = "VIDEO REQUIRED")
    private String videoId;

    private VideoLikeType type;
}
