package com.example.entity;

import com.example.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "preview_attach_id")
    private String previewId;
    @JoinColumn(name = "preview_attach_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity preview;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "category_id")
    private Integer categoryId;
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @Column(name = "attach_id")
    private String attachId;
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDateTime publishYear;

    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "dislike_count")
    private Integer dislikeCount;

    @Column(name = "shared_count")
    private Integer sharedCount;

    @Column(name = "channel_id")
    private String channelId;
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;


}
