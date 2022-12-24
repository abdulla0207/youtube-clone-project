package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ChannelEntity;
import com.example.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {

    private String id;

    private String previewId;

    private String title;

    private String description;

    private Integer categoryId;

    private String attachId;

    private LocalDateTime publishYear;

    private VideoStatus status;

    private Integer viewCount;

    private Integer likeCount;

    private Integer dislikeCount;

    private Integer sharedCount;

    private String channelId;

}
