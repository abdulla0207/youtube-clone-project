package com.example.dto;

import com.example.entity.TagEntity;
import com.example.entity.VideoTagEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagDTO {

    private String id;
    @NotBlank
    @Size(message = "VIDEO required")
    private String videoId;
    @NotBlank
    @Size(message = "TAG required")
    private Integer tagId;

}
