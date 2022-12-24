package com.example.entity;

import com.example.enums.VideoLikeType;
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
@Table(name = "video_like")
public class VideoLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private VideoTagEntity video;

    @Enumerated(EnumType.STRING)
    private VideoLikeType type;


}
