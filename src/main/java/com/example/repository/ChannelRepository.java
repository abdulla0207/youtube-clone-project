package com.example.repository;


import com.example.dto.AttachDTO;
import com.example.entity.ChannelEntity;
import com.example.service.ChannelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


public interface ChannelRepository extends JpaRepository<ChannelEntity, UUID> {

    @Transactional
    @Modifying
    @Query("update ChannelEntity set name = ?1, description = ?2, photoId = ?3, bannerId = ?4 where id = ?5")
    int updateChannel(String name, String description, String attachId, String bannerId, String channelId);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set photoId = ?1 where id = ?2")
    int updateChannelPhoto(String id, String channelId);


    @Query("update ChannelEntity set bannerId = ?1 where id = ?2")
    int updateChannelBanner(AttachDTO banner, String channelId);
}
