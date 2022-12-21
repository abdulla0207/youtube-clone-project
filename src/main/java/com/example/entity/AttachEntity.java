package com.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Integer size;
    @Column
    private String type;
    @Column
    private String path;
    @Column
    private Integer duration;
}
