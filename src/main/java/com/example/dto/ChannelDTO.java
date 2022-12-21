package com.example.dto;

import com.example.entity.ProfileEntity;
import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {
    private String id;
    private String name;
    private String description;
    private String photo;      /// type ????

    private ChannelStatus status;
    private String banner;/// type ????

    private Integer profileId;

}
