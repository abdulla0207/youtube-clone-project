package com.example.entity;

import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "photo_id")
    private String photoId;
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity photo;

    @Column
    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column(name = "banner_id")
    private String bannerId;
    @JoinColumn(name = "banner_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity banner;

    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

}
