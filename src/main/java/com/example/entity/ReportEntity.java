package com.example.entity;

import com.example.enums.ReportType;
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
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @Column
    private String content;

    @Column(name = "channel_id")
    private String channelId;
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;

    @Column
    private ReportType type;
}
