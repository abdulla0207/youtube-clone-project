package com.example.controller;


import com.example.dto.AttachDTO;
import com.example.dto.ChannelDTO;
import com.example.entity.ChannelEntity;
import com.example.service.ChannelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/user/create")
    public ResponseEntity<?> createChannel(@RequestParam ChannelDTO channelDTO){
        return ResponseEntity.ok(channelService.createChannel(channelDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER')")
    @PutMapping("/update")
    public ResponseEntity<?> updateChannel(@PathVariable String channelId,
            @RequestParam ChannelDTO channelDTO){
        int response = channelService.updateChannel(channelId, channelDTO);

        if(response == 1){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER')")
    @PutMapping("/update/photo/{id}")
    public ResponseEntity<?> changeChannelPhoto(@PathVariable("id") String channelId, @RequestParam AttachDTO attachDTO){
        String response = channelService.updateChannelPhoto(channelId, attachDTO);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER')")
    @PutMapping("/update/banner/{id}")
    public ResponseEntity<?> changeChannelBanner(@Valid @RequestBody ChannelDTO channelDTO, @PathVariable("id") String channelId){
        String response = channelService.changeChannelBanner(channelDTO, channelId);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pagination")
    public ResponseEntity<?> channelListPagination(@RequestParam Integer page, @RequestParam Integer size){
        Page<ChannelDTO> channelEntityPage = channelService.channelListPagination(page, size);
        return ResponseEntity.ok(channelEntityPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChannelById(@PathVariable("id") String channelId){
        return ResponseEntity.ok(channelService.getChannelById(channelId));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_OWNER', 'ROLE_OWNER')")
    @PutMapping("/update/status/{id}")
    public ResponseEntity<?> changeChannelStatus(){

    }

}
