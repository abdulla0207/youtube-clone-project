package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
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
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "attach_id")
    private String attachId;
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "prt_Id")
    private Integer prtId;

    @Column
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
