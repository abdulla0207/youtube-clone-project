package com.example.entity;

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
@Table(name = "video_tag")
public class VideoTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "video_id")
    private String videoId;
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private VideoEntity video;

    @Column(name = "tag_id")
    private Integer tagId;
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private TagEntity tag;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
