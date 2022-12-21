package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {
}
