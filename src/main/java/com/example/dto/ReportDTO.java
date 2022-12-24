package com.example.dto;

import com.example.entity.ChannelEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ReportType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDTO {

    private Integer id;
    @NotBlank
    @Size(message = "Profile REQUIRED")
    private Integer profileId;

    @NotBlank
    @Size(min = 4, message = "Content is required")
    private String content;

    @NotBlank
    @Size(message = "channel REQUIRED")
    private String channelId;

    private ReportType type;
}
