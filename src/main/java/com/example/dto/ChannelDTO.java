package com.example.dto;

import com.example.enums.ChannelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    private String name;
    private String description;
    private String photo;      /// type ????

    private ChannelStatus status;
    private AttachDTO banner;/// type ????

    private Integer profileId;

}
