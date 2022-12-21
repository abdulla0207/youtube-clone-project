package com.example.entity;

import com.example.enums.PlaylistStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private PlaylistStatus status;

    @Column(name = "channel_id")
    private Integer channelId;
    @JoinColumn(name = "channel_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;


    ////
    @Column(name = "order_num")
    private Integer orderNum;
}
