package com.example.dto;

import com.example.entity.ProfileEntity;
import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {
    private String id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Name is required")
    private String name;
    @NotBlank
    @Size(min = 5, message = "description is required")
    private String description;

    private String photo;      /// type ????

    private ChannelStatus status;
    private String banner;/// type ????

    private Integer profileId;

}
