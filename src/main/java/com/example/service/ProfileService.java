package com.example.service;

import com.example.dto.AttachDTO;
import com.example.dto.ChangeDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exceptions.AppForBiddenExceptions;
import com.example.exceptions.EmailAlreadyExistsException;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import com.example.util.SpringSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProfileService {


    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AttachService attachService;

    public ProfileEntity getById(String id) {
        log.info("Profile get -> " + id);
        return profileRepository.findById(UUID.fromString(id)).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Profile Not Found");
        });
    }


    public ProfileDTO get(String id) {
        ProfileEntity entity = getById(id);

        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        if (Optional.ofNullable(entity.getAttach()).isPresent()) {
            AttachDTO attachDTO = new AttachDTO(attachService.toOpenUrl(entity.getAttach().getId().toString()));
            dto.setImage(attachDTO);
        }

        return dto;
    }

    public ProfileDTO create(ProfileDTO profileDTO) {
        Optional<ProfileEntity> profile = profileRepository.findByEmail(profileDTO.getEmail());

        if (profile != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        ProfileEntity entity = toEntity(profileDTO);
        entity.setPrtId(SpringSecurityUtil.getCurrentUserId());

        profileRepository.save(entity);

        profileDTO.setId(entity.getId());
        return profileDTO;

    }

    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setPassword(MD5Util.encode(profileDTO.getPassword()));
        entity.setRole(profileDTO.getRole());
        entity.setVisible(true);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
//        entity.setPhoto(attachService.get(profileDTO.getPhotoId()));
        return entity;
    }

    public ChangeDTO change(ChangeDTO dto) {
        ProfileEntity exists = profileRepository.findByPassword(MD5Util.encode(dto.getOldPassword()));
        if (exists == null) {
            log.info("Not Found password -> " + dto);
            throw new ItemNotFoundException("Not found");
        }
        int b = profileRepository.change(exists.getId(), MD5Util.encode(dto.getNewPassword()));
        if (b == 0) {
            throw new AppForBiddenExceptions("Password or Email wrong!");
        }
        return dto;
    }

    public int updateUser(ProfileDTO profileDTO) {
        return profileRepository.updateUserById(profileDTO.getName(), profileDTO.getSurname(), SpringSecurityUtil.getCurrentUserId());
    }


    public int updateAttach(ProfileDTO profileDTO) {
        return profileRepository.updateAttach(profileDTO.getAttachId(), SpringSecurityUtil.getCurrentUserId());
    }

    public int updateEmail(ProfileDTO profileDTO) {
        return profileRepository.updateEmail(profileDTO.getEmail(), SpringSecurityUtil.getCurrentUserId());
    }
}

