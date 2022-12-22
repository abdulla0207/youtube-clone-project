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
@Table(name = "attach")
public class AttachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "origin_name")
    private String originName ;

    @Column
    private Long size;

    @Column
    private String type;

    @Column
    private String path;

    @Column
    private String extension;

    @Column
    private Integer duration;
    @Column
    private String url;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
