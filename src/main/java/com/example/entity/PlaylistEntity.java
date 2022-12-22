package com.example.entity;

import com.example.enums.PlaylistStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private PlaylistStatus status;

    @Column(name = "channel_id")
    private String channelId;
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;

    @Column(name = "order_num")
    private Integer orderNum;
}
