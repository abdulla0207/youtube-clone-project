package com.example.controller;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.service.AttachService;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

    @RestController
    @RequestMapping("/init")
    public class InitController {

        @Autowired
        private ProfileRepository profileRepository;

        @Autowired
        private AttachService attachService;

        @GetMapping("/data")
        public String adminCreate(){
            Optional<ProfileEntity> optional = profileRepository.findAllByRole(ProfileRole.ROLE_ADMIN);
            if(!optional.isEmpty())
                throw new ItemNotFoundException("admin already created");

            ProfileEntity entity = new ProfileEntity();
            entity.setName("You");
            entity.setSurname("Tube");
            entity.setPassword(MD5Util.encode("7777"));
            entity.setEmail("youtubeUser@gmail.com");

            // bitta attach upload qilib nullni orniga uuidni qoyish kerak
            entity.setAttach(attachService.get(null));
            
            entity.setRole(ProfileRole.ROLE_ADMIN);
            entity.setStatus(ProfileStatus.ACTIVE);
            profileRepository.save(entity);
            return "Admin added";
        }

    }


