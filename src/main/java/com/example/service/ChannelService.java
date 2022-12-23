package com.example.service;

import com.example.dto.AttachDTO;
import com.example.dto.ChannelDTO;
import com.example.entity.AttachEntity;
import com.example.entity.ChannelEntity;
import com.example.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public ChannelDTO createChannel(ChannelDTO channelDTO) {

        ChannelEntity channelEntity = getEntity(channelDTO);

        channelRepository.save(channelEntity);
        channelDTO.setId(channelEntity.getId());
        return channelDTO;
    }

    public int updateChannel(String channelId, ChannelDTO channelDTO) {
        return channelRepository.updateChannel(channelDTO.getName(), channelDTO.getDescription(),
                channelDTO.getAttachId(), channelDTO.getBannerId(), channelId);
    }


    private ChannelEntity getEntity(ChannelDTO channelDTO){
        ChannelEntity channelEntity = new ChannelEntity();
        channelEntity.setName(channelDTO.getName());
        channelEntity.setBannerId(channelDTO.getBannerId());
        channelEntity.setDescription(channelDTO.getDescription());
        channelEntity.setPhotoId(channelDTO.getAttachId());
        channelEntity.setProfileId(channelDTO.getProfileId());
        channelEntity.setStatus(channelDTO.getStatus());

        return channelEntity;
    }

    public String updateChannelPhoto(String channelId, AttachDTO attachDTO) {
        int changePhoto = channelRepository.updateChannelPhoto(attachDTO.getId(), channelId);

        if(changePhoto == 0){
            throw new RuntimeException("Could not update channel photo.");
        }

        return "Updated";
    }

    public Page<ChannelDTO> channelListPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ChannelEntity> channelEntityPage = channelRepository.findAll(pageable);
        List<ChannelEntity> channelEntityList = channelEntityPage.getContent();

        List<ChannelDTO> channelDTOList = new ArrayList<>();

        channelEntityList.forEach(channelEntity -> {
            ChannelDTO channelDTO = new ChannelDTO();
            channelDTO.setDescription(channelEntity.getDescription());
            channelDTO.setName(channelEntity.getName());
            channelDTO.setStatus(channelEntity.getStatus());
            channelDTO.setBannerId(channelEntity.getBannerId());
            channelDTO.setProfileId(channelEntity.getProfileId());
            channelDTO.setAttachId(channelEntity.getPhotoId());
            channelDTOList.add(channelDTO);
        });

        return new PageImpl<>(channelDTOList, pageable, channelEntityPage.getTotalElements());
    }
}
