package com.example.service;

import com.example.dto.auth.AuthRegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exceptions.ProfileAlreadyExistException;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;


    public String registration(AuthRegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getSurname());

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if(profile.getStatus().equals(ProfileStatus.NOT_ACTIVE)){
                profileRepository.delete(profile);
            }else {
                throw new ProfileAlreadyExistException("Profile already exist");
            }
        }

        ProfileEntity profile = new ProfileEntity();
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword());
        profile.setRole(ProfileRole.ROLE_USER);
        profile.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(profile);

        return "Message sent your emails' ";
    }
}
