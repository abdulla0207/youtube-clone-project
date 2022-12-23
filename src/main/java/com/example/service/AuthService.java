package com.example.service;

import com.example.dto.auth.AuthLoginDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.Language;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exceptions.AppBadRequestException;
import com.example.exceptions.AppForBiddenExceptions;
import com.example.exceptions.EmailAlreadyExistsException;
import com.example.exceptions.ProfileCreateException;
import com.example.repository.ProfileRepository;
import com.example.util.JwtTokenUtil;
import com.example.util.MD5Util;
import com.example.util.SpringSecurityUtil;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ResourceBundleService resourceService;


    @Autowired
    private EmailHistoryService emailHistoryService;

    @Autowired
    private ProfileService profileService;

    @Value("muhammadsodiqnabijonov2502@gmail.com")
    private String fromAccount;


    public AuthResponseDTO login(AuthLoginDTO dto, Language language) {

        ProfileEntity profile = profileRepository.findByEmailAndPassword(dto.getEmail(), MD5Util.encode(dto.getPassword()));

        if (profile==null) {
            throw new AppBadRequestException(resourceService.getMessage("Credential wrong", language.name()));
        }


        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppForBiddenExceptions("No Access");
        }


        return toResponseDTO(profile);
    }

    private AuthResponseDTO toResponseDTO(ProfileEntity profile) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setName(profile.getName());
        authResponseDTO.setSurname(profile.getSurname());
        authResponseDTO.setRole(profile.getRole());
        authResponseDTO.setToken(JwtTokenUtil.encode(profile.getName(),profile.getRole()));
        return authResponseDTO;
    }

    public String register(RegistrationDTO dto) {
        Optional<ProfileEntity> profile = profileRepository.findByEmail(dto.getEmail());

        if (profile.isEmpty()) {
                throw new EmailAlreadyExistsException("Email already exist");
        }


        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());

        String password = MD5Util.encode(dto.getPassword());
        entity.setPassword(password);

        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setAttach(null);
        profileRepository.save(entity);

        StringBuilder sb = new StringBuilder();
        sb.append("<h1 style=\"text-align: center\">Complete Registration</h1>");
        String link = String.format("<a href=\"http://localhost:8080/authorization/verification/email/%s\"> Click there </a>",
                JwtTokenUtil.encode2(SpringSecurityUtil.getCurrentUserId()));
        sb.append(link);

        emailHistoryService.sendEmailMime(fromAccount,dto.getEmail(), "Complete registration", sb.toString());


        if (entity.getId() == 0) {
            throw new ProfileCreateException("Something went wrong");
        }
        return "Successfully registered";
    }


    public String verification(String jwt) {
        Integer id;
        try {
            id = JwtTokenUtil.decodeForEmailVerification(jwt);
        } catch (JwtException e) {
            return "Verification failed";
        }

        ProfileEntity exists = profileService.getById(String.valueOf(id));
        if (!exists.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            return "Verification failed";
        }

        exists.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(exists);

        return "Verification success";
    }
}
