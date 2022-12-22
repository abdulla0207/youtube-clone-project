package com.example.dto;

import com.example.entity.ChannelEntity;
import com.example.enums.PlaylistStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {

    private Integer id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Name is required")
    private String name;
    @NotBlank
    @Size(min = 5, message = "description is required")
    private String description;

    private PlaylistStatus status;

    private Integer channelId;
    private ChannelDTO channel;

    private Integer orderNum;
}
