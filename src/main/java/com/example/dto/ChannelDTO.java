package com.example.dto;

import com.example.enums.ChannelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Name is required")
    private String name;
    @NotBlank
    @Size(min = 5, message = "description is required")
    private String description;
    private ChannelStatus status;

    private AttachDTO photo;
    private String attachId;

    private AttachDTO banner;
    private String bannerId;

    private ProfileDTO profile;
    private Integer profileId;

}
