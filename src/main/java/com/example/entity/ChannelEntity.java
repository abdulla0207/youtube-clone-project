package com.example.entity;

import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String photo;/// type ????

    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column
    private String banner;/// type ????

    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

}
