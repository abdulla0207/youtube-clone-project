package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ChannelEntity;
import com.example.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {

    private String id;

    @NotBlank
    @Size(message = "Preview required")
    private String previewId;
    @NotBlank
    @Size(min = 5, max = 20, message = "title is required")
    private String title;

    @Size(min = 5, message = "description is required")
    private String description;

    @NotBlank
    @Size(message = "Category required")
    private Integer categoryId;

    @NotBlank
    @Size(message = "photo required")
    private String attachId;

    @NotBlank
    @Size(message = "publishedYear required")
    private LocalDateTime publishYear;

    @NotBlank
    @Size(message = "channel required")
    private String channelId;

    private VideoStatus status;


    private Integer viewCount;

    private Integer likeCount;

    private Integer dislikeCount;

    private Integer sharedCount;



}
