package com.example.dto;

import com.example.entity.ChannelEntity;
import com.example.enums.PlaylistStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {

    private Integer id;

    private String name;

    private String description;

    private PlaylistStatus status;

    private Integer channelId;
    ////
    @Column(name = "order_num")
    private Integer orderNum;
}
